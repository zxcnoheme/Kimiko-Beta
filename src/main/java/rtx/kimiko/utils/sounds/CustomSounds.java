/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.StringCompanionObject
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.sound.SoundCategory
 *  net.minecraft.client.sound.OggAudioStream
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.sounds;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.client.sound.OggAudioStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002TUB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ#\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0017\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0015H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J-\u0010\u0017\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0015H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u0017\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\nH\u0002\u00a2\u0006\u0004\b \u0010\u0003J\u000f\u0010!\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b!\u0010\u0003J\u000f\u0010\"\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\"\u0010\u0003J\u000f\u0010#\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0019\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0019\u0010(\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b(\u0010'J\u0017\u0010)\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b)\u0010'J\u0017\u0010*\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b*\u0010'J'\u00100\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b0\u00101R\u001f\u00104\u001a\b\u0012\u0004\u0012\u00020\u0004028\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020-8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020-8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u00109R\u0014\u0010>\u001a\u00020-8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u00109R\u0014\u0010@\u001a\u00020?8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020?8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0014\u0010C\u001a\u00020-8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u00109R\u0014\u0010D\u001a\u00020?8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010AR\u001c\u0010G\u001a\n F*\u0004\u0018\u00010E0E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001c0I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR \u0010M\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020%0L8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0018\u0010P\u001a\u0004\u0018\u00010O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010S\u00a8\u0006V"}, d2={"Lrtx/kimiko/utils/sounds/CustomSounds;", "", "<init>", "()V", "", "category", "Ljava/nio/file/Path;", "Lkotlin/jvm/JvmStatic;", "folder", "(Ljava/lang/String;)Ljava/nio/file/Path;", "", "ensureFolder", "(Ljava/nio/file/Path;)V", "", "list", "(Ljava/nio/file/Path;)Ljava/util/List;", "name", "", "isSupported", "(Ljava/lang/String;)Z", "file", "", "volume", "play", "(Ljava/nio/file/Path;F)V", "pitch", "(Ljava/nio/file/Path;FF)V", "invalidate", "Lrtx/kimiko/utils/sounds/CustomSounds$Voice;", "voice", "submit", "(Lrtx/kimiko/utils/sounds/CustomSounds$Voice;)V", "ensureMixer", "mixLoop", "restartMixer", "masterVolume", "()F", "Lrtx/kimiko/utils/sounds/CustomSounds$Sample;", "sample", "(Ljava/nio/file/Path;)Lrtx/kimiko/utils/sounds/CustomSounds$Sample;", "decode", "decodeSampled", "decodeVorbis", "", "interleaved", "", "channels", "rate", "resample", "([SIF)[S", "", "Lkotlin/jvm/JvmField;", "EXTENSIONS", "Ljava/util/List;", "LINE_RATE", "F", "LINE_CHANNELS", "I", "Ljavax/sound/sampled/AudioFormat;", "LINE_FORMAT", "Ljavax/sound/sampled/AudioFormat;", "CHUNK_FRAMES", "MAX_VOICES", "", "IDLE_CLOSE_MS", "J", "MAX_FILE_BYTES", "MAX_CACHED", "MAX_LISTED", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "LOADER", "Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "VOICES", "Ljava/util/concurrent/CopyOnWriteArrayList;", "", "CACHE", "Ljava/util/Map;", "Ljava/lang/Thread;", "mixer", "Ljava/lang/Thread;", "audioBroken", "Z", "Sample", "Voice", "rtx.kimiko:kimiko"})
public final class CustomSounds {
    @NotNull
    public static final CustomSounds INSTANCE = new CustomSounds();
    @JvmField
    @NotNull
    public static final List<String> EXTENSIONS;
    private static final float LINE_RATE = 44100.0f;
    private static final int LINE_CHANNELS = 2;
    @NotNull
    private static final AudioFormat LINE_FORMAT;
    private static final int CHUNK_FRAMES = 1024;
    private static final int MAX_VOICES = 8;
    private static final long IDLE_CLOSE_MS = 4000L;
    private static final long MAX_FILE_BYTES = 0x1800000L;
    private static final int MAX_CACHED = 24;
    private static final long MAX_LISTED = 512L;
    private static final ExecutorService LOADER;
    @NotNull
    private static final CopyOnWriteArrayList<Voice> VOICES;
    @NotNull
    private static final Map<String, Sample> CACHE;
    @Nullable
    private static Thread mixer;
    private static volatile boolean audioBroken;

    private CustomSounds() {
    }

    @JvmStatic
    @NotNull
    public static final Path folder(@NotNull String category) {
        Intrinsics.checkNotNullParameter((Object)category, (String)"category");
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve(category).toAbsolutePath().normalize();
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"normalize(...)");
        return path;
    }

    @JvmStatic
    public static final void ensureFolder(@NotNull Path folder) {
        Intrinsics.checkNotNullParameter((Object)folder, (String)"folder");
        try {
            Files.createDirectories(folder, new FileAttribute[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @NotNull
    public static final List<String> list(@Nullable Path folder) {
        ArrayList<String> names = new ArrayList<>();
        if (folder == null || !Files.isDirectory(folder, new LinkOption[0])) {
            return names;
        }
        try (Stream<Path> stream = Files.list(folder)) {
            stream.filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .filter(CustomSounds::isSupported)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .limit(512L)
                .forEach(names::add);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return names;
    }

    @JvmStatic
    public static final boolean isSupported(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        String string = name;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String lower = string2;
        for (String extension : EXTENSIONS) {
            if (!String.valueOf(lower).endsWith(extension)) continue;
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final void play(@Nullable Path file, float volume) {
        CustomSounds.play(file, volume, 1.0f);
    }

    @JvmStatic
    public static final void play(@Nullable Path file, float volume, float pitch) {
        if (file == null || audioBroken) {
            return;
        }
        float gain = volume * INSTANCE.masterVolume();
        if (gain <= 0.001f) {
            return;
        }
        float step = RangesKt.coerceIn((float)pitch, (float)0.25f, (float)4.0f);
        LOADER.execute(() -> CustomSounds.play$lambda$0(file, gain, step));
    }

    @JvmStatic
    public static final void invalidate() {
        CACHE.clear();
    }

    private final void submit(Voice voice) {
        while (VOICES.size() >= 8) {
            Voice oldest;
            Voice voice2 = oldest = VOICES.isEmpty() ? null : VOICES.get(0);
            if (oldest != null && VOICES.remove(oldest)) continue;
        }
        VOICES.add(voice);
        this.ensureMixer();
    }

    private final synchronized void ensureMixer() {
        Thread current = mixer;
        if (audioBroken || current != null && current.isAlive()) {
            return;
        }
        Thread thread = new Thread(CustomSounds::ensureMixer$lambda$0, "kimiko-sound-mixer");
        thread.setDaemon(true);
        mixer = thread;
        thread.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    private final void mixLoop() {
        SourceDataLine line = null;
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, LINE_FORMAT);
            if (!AudioSystem.isLineSupported(info)) {
                audioBroken = true;
                return;
            }
            Line line2 = AudioSystem.getLine(info);
            Intrinsics.checkNotNull((Object)line2, "null cannot be cast to non-null type javax.sound.sampled.SourceDataLine");
            line = (SourceDataLine)line2;
            line.open(LINE_FORMAT, 16384);
            line.start();
            float[] accumulator = new float[2048];
            byte[] out = new byte[4096];
            long idleSince = System.currentTimeMillis();
            while (true) {
                if (VOICES.isEmpty()) {
                    if (System.currentTimeMillis() - idleSince > 4000L) break;
                    Thread.sleep(15L);
                    continue;
                }
                idleSince = System.currentTimeMillis();
                Arrays.fill(accumulator, 0.0f);
                Iterator<Voice> iterator = VOICES.iterator();
                while (iterator.hasNext()) {
                    Voice voice = iterator.next();
                    if (!voice.mix(accumulator)) {
                        iterator.remove();
                    }
                }
                int n = accumulator.length;
                for (int i = 0; i < n; ++i) {
                    int value = Math.round(accumulator[i]);
                    if (value > Short.MAX_VALUE) {
                        value = Short.MAX_VALUE;
                    } else if (value < Short.MIN_VALUE) {
                        value = Short.MIN_VALUE;
                    }
                    out[i * 2] = (byte)(value & 0xFF);
                    out[i * 2 + 1] = (byte)(value >> 8 & 0xFF);
                }
                line.write(out, 0, out.length);
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        } catch (Throwable t) {
            audioBroken = true;
        } finally {
            if (line != null) {
                try {
                    line.drain();
                    line.stop();
                    line.close();
                } catch (Throwable ignored) {}
            }
            if (audioBroken) {
                VOICES.clear();
            } else if (!VOICES.isEmpty()) {
                this.restartMixer();
            }
        }
    }

    private final synchronized void restartMixer() {
        mixer = null;
        this.ensureMixer();
    }

    private final float masterVolume() {
        float f;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.options == null) {
            return 1.0f;
        }
        try {
            f = minecraft.options.getSoundVolume(SoundCategory.BLOCKS);
        }
        catch (Throwable throwable) {
            f = 1.0f;
        }
        return f;
    }

    private final Sample sample(Path file) {
        Sample sample;
        String key = ((Object)file.toAbsolutePath().normalize()).toString();
        Sample cached = CACHE.get(key);
        if (cached != null) {
            return cached;
        }
        try {
            if (!Files.isRegularFile(file, new LinkOption[0]) || Files.size(file) > 0x1800000L) {
                return null;
            }
            Sample decoded = this.decode(file);
            if (decoded != null && !(decoded.pcm.length == 0)) {
                CACHE.put(key, decoded);
            }
            sample = decoded;
        }
        catch (Throwable throwable) {
            sample = null;
        }
        return sample;
    }

    private final Sample decode(Path file) throws Exception {
        String string = ((Object)file.getFileName()).toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String name = string2;
        return String.valueOf(name).endsWith(".ogg") ? this.decodeVorbis(file) : this.decodeSampled(file);
    }

    private final Sample decodeSampled(Path file) throws Exception {
        try (BufferedInputStream raw = new BufferedInputStream(Files.newInputStream(file, new OpenOption[0]));
             AudioInputStream input = AudioSystem.getAudioInputStream(raw)) {
            AudioFormat source = input.getFormat();
            int channels = Math.max(1, source.getChannels());
            float rate = source.getSampleRate() > 0.0f ? source.getSampleRate() : 44100.0f;
            AudioFormat target = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, rate, 16, channels, channels * 2, rate, false);
            byte[] bytes;
            if (source.matches(target)) {
                bytes = input.readAllBytes();
            } else {
                try (AudioInputStream converted = AudioSystem.getAudioInputStream(target, input)) {
                    bytes = converted.readAllBytes();
                }
            }
            short[] interleaved = new short[bytes.length / 2];
            ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(interleaved);
            return new Sample(INSTANCE.resample(interleaved, channels, rate));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Sample decodeVorbis(Path file) throws Exception {
        Closeable closeable = new BufferedInputStream(Files.newInputStream(file, new OpenOption[0]));
        Throwable throwable = null;
        try {
            Sample sample;
            BufferedInputStream raw = (BufferedInputStream)closeable;
            boolean bl = false;
            Closeable closeable2 = (Closeable)new OggAudioStream((InputStream)raw);
            Throwable throwable2 = null;
            try {
                OggAudioStream stream = (OggAudioStream)closeable2;
                boolean bl2 = false;
                AudioFormat audioFormat = stream.getFormat();
                Intrinsics.checkNotNullExpressionValue((Object)audioFormat, (String)"getFormat(...)");
                AudioFormat format = audioFormat;
                int channels = Math.max(1, format.getChannels());
                float rate = format.getSampleRate() > 0.0f ? format.getSampleRate() : 44100.0f;
                ByteBuffer byteBuffer = stream.readAll();
                Intrinsics.checkNotNullExpressionValue((Object)byteBuffer, (String)"readAll(...)");
                ByteBuffer decoded = byteBuffer;
                short[] interleaved = new short[decoded.remaining() / 2];
                decoded.order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(interleaved);
                sample = new Sample(INSTANCE.resample(interleaved, channels, rate));
            }
            catch (Throwable throwable3) {
                try {
                    try {
                        throwable2 = throwable3;
                        throw throwable3;
                    }
                    catch (Throwable throwable4) {
                        CloseableKt.closeFinally((Closeable)closeable2, throwable2);
                        throw throwable4;
                    }
                }
                catch (Throwable throwable5) {
                    throwable = throwable5;
                    throw throwable5;
                }
            }
            CloseableKt.closeFinally((Closeable)closeable2, (Throwable)throwable2);
            Sample sample2 = sample;
            return sample2;
        }
        finally {
            CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
        }
    }

    private final short[] resample(short[] interleaved, int channels, float rate) {
        int frames = interleaved.length / channels;
        if (frames <= 0) {
            return new short[0];
        }
        double ratio = rate / 44100.0f;
        int outFrames = Math.max(1, (int)Math.floor((double)frames / ratio));
        short[] out = new short[outFrames * 2];
        for (int i = 0; i < outFrames; ++i) {
            double src = (double)i * ratio;
            int i0 = (int)src;
            int i1 = Math.min(frames - 1, i0 + 1);
            float frac = (float)(src - (double)i0);
            for (int channel = 0; channel < 2; ++channel) {
                int sourceChannel = Math.min(channels - 1, channel);
                float a = interleaved[i0 * channels + sourceChannel];
                float b = interleaved[i1 * channels + sourceChannel];
                out[i * 2 + channel] = (short)Math.round(a + (b - a) * frac);
            }
        }
        return out;
    }

    private static final Thread LOADER$lambda$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "kimiko-sound-loader");
        thread.setDaemon(true);
        return thread;
    }

    private static final void play$lambda$0(Path $file, float $gain, float $step) {
        Sample sample = INSTANCE.sample($file);
        if (sample == null || sample.pcm.length == 0) {
            return;
        }
        INSTANCE.submit(new Voice(sample.pcm, $gain, $step));
    }

    private static final void ensureMixer$lambda$0() {
        INSTANCE.mixLoop();
    }

    static {
        EXTENSIONS = List.of(".wav", ".ogg", ".aiff", ".aif", ".au", ".snd");
        LINE_FORMAT = new AudioFormat(44100.0f, 16, 2, true, false);
        LOADER = Executors.newSingleThreadExecutor(CustomSounds::LOADER$lambda$0);
        VOICES = new CopyOnWriteArrayList();
        Map map = Collections.synchronizedMap(new LinkedHashMap<String, Sample>(){

            protected boolean removeEldestEntry(Map.Entry<String, Sample> eldest) {
                Intrinsics.checkNotNullParameter(eldest, (String)"eldest");
                return this.size() > 24;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, (String)"synchronizedMap(...)");
        CACHE = map;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/sounds/CustomSounds$Sample;", "", "", "pcm", "<init>", "([S)V", "Lkotlin/jvm/JvmField;", "[S", "rtx.kimiko:kimiko"})
    private static final class Sample {
        @JvmField
        @NotNull
        public final short[] pcm;

        public Sample(@NotNull short[] pcm) {
            Intrinsics.checkNotNullParameter((Object)pcm, (String)"pcm");
            this.pcm = pcm;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/sounds/CustomSounds$Voice;", "", "", "pcm", "", "gain", "", "step", "<init>", "([SFD)V", "", "accumulator", "", "mix", "([F)Z", "[S", "F", "D", "position", "rtx.kimiko:kimiko"})
    private static final class Voice {
        @NotNull
        private final short[] pcm;
        private final float gain;
        private final double step;
        private double position;

        public Voice(@NotNull short[] pcm, float gain, double step) {
            Intrinsics.checkNotNullParameter((Object)pcm, (String)"pcm");
            this.pcm = pcm;
            this.gain = gain;
            this.step = step;
        }

        public final boolean mix(@NotNull float[] accumulator) {
            Intrinsics.checkNotNullParameter((Object)accumulator, (String)"accumulator");
            int frames = this.pcm.length / 2;
            for (int i = 0; i < accumulator.length; i += 2) {
                int index = (int)this.position;
                if (index >= frames) {
                    return false;
                }
                for (int channel = 0; channel < 2; ++channel) {
                    int n = i + channel;
                    accumulator[n] = accumulator[n] + (float)this.pcm[index * 2 + channel] * this.gain;
                }
                this.position += this.step;
            }
            return true;
        }
    }
}

