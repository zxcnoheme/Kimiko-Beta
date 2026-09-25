/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Unmodifiable
 */
package mods.acountswiher.ru.vidtu.ias.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public final class IASStorage {
    @NotNull
    private static final String DISCLAIMER = "> ENGLISH\nNotification about security of accounts stored in the \"In-Game Account Switcher\" mod:\nUNDER NO CIRCUMSTANCES SHOULD YOU SEND THIS FOLDER TO *ANYONE* (INCLUDING DEVELOPERS OF THIS MOD),\nEVEN IF IT APPEARS THAT THIS FOLDER IS FULLY EMPTY.\nIF YOU ACCIDENTALLY SENT THIS FOLDER TO ANYONE, PLEASE, VISIT THE FOLLOWING WEBSITE:\nhttps://account.microsoft.com/security\nAND CHANGE YOUR PASSWORD, THEN VISIT THE FOLLOWING WEBSITE:\nhttps://account.live.com/consent/manage\nAND REVOKE THE PERMISSIONS (ACCESS) TO THE \"In-Game Account Switcher\" APPLICATION,\nAND/OR ANY OTHER THAT YOU DO CAN'T RECOGNIZE OR YOU SUSPECT IT COULD ACCESS YOUR GAME ACCOUNT.\nAFTER REVOKING ACCESS YOU SHOULD *NOT* USE THIS MODIFICATION FOR 31 DAYS.\n(If you suspect someone has got access to your game account, revoke ALL permissions\nfor ALL applications and do *NOT* launch the game for 31 days at all)\n\n> РУССКИЙ (RUSSIAN)\nУведомление о безопасности аккаунтов из мода \"In-Game Account Switcher\":\nНИ ПРИ КАКИХ ОБСТОЯТЕЛЬСТВАХ НЕ ОТПРАВЛЯЙТЕ ЭТУ ПАПКУ *КОМУ-ЛИБО* (В ТОМ ЧИСЛЕ И РАЗРАБОТЧИКАМ ЭТОГО МОДА),\nДАЖЕ ЕСЛИ ВАМ КАЖЕТСЯ, ЧТО ЭТА ПАПКА ПОЛНОСТЬЮ ПУСТАЯ.\nЕСЛИ ВЫ СЛУЧАЙНО ОТПРАВИЛИ ЭТУ ПАПКУ КОМУ-ЛИБО, ПОЖАЛУЙСТА, ЗАЙДИТЕ НА СЛЕДУЮЩИЙ ВЕБСАЙТ:\nhttps://account.microsoft.com/security\nИ СМЕНИТЕ СВОЙ ПАРОЛЬ, ПОТОМ ЗАЙДИТЕ НА СЛЕДУЮЩИЙ ВЕБСАЙТ:\nhttps://account.live.com/consent/manage\nИ ОТЗОВИТЕ РАЗРЕШЕНИЯ (ДОСТУП) К ПРИЛОЖЕНИЮ \"In-Game Account Switcher\"\nИ/ИЛИ ЛЮБОМУ ДРУГОМУ, КОТОРОЕ ВЫ НЕ МОЖЕТЕ ОПОЗНАТЬ ИЛИ ПОДОЗРЕВАЕТЕ, ЧТО ОНО МОЖЕТ\nПОЛУЧИТЬ ДОСТУП К ВАШЕМУ ИГРОВОМУ АККАУНТУ.\nПОСЛЕ ОТЗЫВА ДОСТУПА ВЫ *НЕ* ДОЛЖНЫ ИСПОЛЬЗОВАТЬ ЭТУ МОДИФИКАЦИЮ КАК МИНИМУМ 31 ДЕНЬ.\n(Если вы подозреваете, что кто-то получил доступ к вашему игровому аккаунту, отзовите ВСЕ разрешения\nдля ВСЕХ приложений и *НЕ* запускайте игру вообще как минимум 31 день)\n";
    @NotNull
    private static final @Unmodifiable List<String> DISCLAIMER_FILE_NAMES = List.of("READ_ME_IMPORTANT.txt", "ПРОЧТИ_МЕНЯ_ВАЖНО.txt");
    @NotNull
    public static final List<Account> ACCOUNTS = new ArrayList<Account>(0);
    public static boolean gameDisclaimerShown = false;

    @Contract(value="-> fail", pure=true)
    private IASStorage() {
        throw new AssertionError((Object)"No instances.");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void disclaimers(@NotNull Path path) {
        try {
            path = path.resolve("_IAS_ACCOUNTS_DO_NOT_SEND_TO_ANYONE");
            Files.createDirectories(path, new FileAttribute[0]);
            byte[] bytes = DISCLAIMER.getBytes(StandardCharsets.UTF_8);
            for (String name : DISCLAIMER_FILE_NAMES) {
                try {
                    Path file = path.resolve(name);
                    if (Files.isRegularFile(file, LinkOption.NOFOLLOW_LINKS) && Files.size(file) == (long)bytes.length) continue;
                    Files.write(file, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, LinkOption.NOFOLLOW_LINKS);
                }
                catch (Throwable t) {
                    if (!name.equals("READ_ME_IMPORTANT.txt")) continue;
                    throw t;
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void load(@NotNull Path path) {
        try {
            Path folder = path.resolve("_IAS_ACCOUNTS_DO_NOT_SEND_TO_ANYONE/.hidden");
            Path file = folder.resolve("accounts_v1.do_not_send_to_anyone");
            gameDisclaimerShown = Files.isRegularFile(folder.resolve("game_disclaimer_shown"), LinkOption.NOFOLLOW_LINKS);
            if (!Files.isRegularFile(file, LinkOption.NOFOLLOW_LINKS)) {
                IASStorage.save(path);
                return;
            }
            file = file.toRealPath(LinkOption.NOFOLLOW_LINKS);
            byte[] data = Files.readAllBytes(file);
            try (DataInputStream in = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(data)));){
                int length = in.readUnsignedShort();
                ArrayList<Account> list = new ArrayList<Account>(length);
                for (int i = 0; i < length; ++i) {
                    list.add(Account.readTyped(in));
                }
                ACCOUNTS.addAll(list);
                HashSet set = new HashSet(ACCOUNTS.size());
                ACCOUNTS.removeIf(Predicate.not(set::add));
            }
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to load IAS storage.", t);
        }
    }

    public static void save(@NotNull Path path) {
        try {
            byte[] data;
            Path file = path.resolve("_IAS_ACCOUNTS_DO_NOT_SEND_TO_ANYONE/.hidden/accounts_v1.do_not_send_to_anyone");
            try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                 DeflaterOutputStream defOut = new DeflaterOutputStream(byteOut);
                 DataOutputStream out = new DataOutputStream(defOut);){
                Account[] list = (Account[])ACCOUNTS.toArray(Account[]::new);
                out.writeShort(list.length);
                for (Account account : list) {
                    Account.writeTyped(out, account);
                }
                defOut.finish();
                data = byteOut.toByteArray();
            }
            Files.createDirectories(file.getParent(), new FileAttribute[0]);
            try {
                Files.setAttribute(file.getParent(), "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                Files.setAttribute(file.getParent(), "dos:system", true, LinkOption.NOFOLLOW_LINKS);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            Files.write(file, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.SYNC, StandardOpenOption.DSYNC, LinkOption.NOFOLLOW_LINKS);
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to save IAS storage.", t);
        }
    }

    public static void gameDisclaimerShown(@NotNull Path path) {
        try {
            gameDisclaimerShown = true;
            Path file = path.resolve("_IAS_ACCOUNTS_DO_NOT_SEND_TO_ANYONE/.hidden/game_disclaimer_shown");
            Files.createDirectories(file.getParent(), new FileAttribute[0]);
            Files.createFile(file, new FileAttribute[0]);
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to mark game disclaimer as shown.", t);
        }
    }
}

