/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.media.LyricLine;
import rtx.kimiko.utils.media.LyricWord;
import rtx.kimiko.utils.media.Lyrics;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J-\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010 \u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0014\u0010'\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010$R\u0014\u0010(\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010)R\u0014\u0010,\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010)R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010.\u00a8\u00060"}, d2={"Lrtx/kimiko/utils/media/LyricsParser;", "", "<init>", "()V", "", "content", "Lrtx/kimiko/utils/media/Lyrics;", "Lkotlin/jvm/JvmStatic;", "parse", "(Ljava/lang/String;)Lrtx/kimiko/utils/media/Lyrics;", "body", "", "start", "end", "Lrtx/kimiko/utils/media/LyricLine;", "plainLine", "(Ljava/lang/String;JJ)Lrtx/kimiko/utils/media/LyricLine;", "offset", "enhanced", "(Ljava/lang/String;JJJ)Lrtx/kimiko/utils/media/LyricLine;", "text", "", "Lrtx/kimiko/utils/media/LyricWord;", "distribute", "(Ljava/lang/String;JJ)Ljava/util/List;", "", "begin", "syllables", "(Ljava/lang/String;II)I", "Ljava/util/regex/Matcher;", "matcher", "group", "timestamp", "(Ljava/util/regex/Matcher;I)J", "Ljava/util/regex/Pattern;", "LINE_TIME", "Ljava/util/regex/Pattern;", "WORD_TIME", "OFFSET", "TAG", "TAIL_MILLIS", "J", "MILLIS_PER_SYLLABLE", "MIN_SUNG_MILLIS", "FULL_SPAN_MILLIS", "CYRILLIC_VOWELS", "Ljava/lang/String;", "LATIN_VOWELS", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nLyricsParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LyricsParser.kt\nrtx/kimiko/utils/media/LyricsParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,210:1\n1174#2,2:211\n*S KotlinDebug\n*F\n+ 1 LyricsParser.kt\nrtx/kimiko/utils/media/LyricsParser\n*L\n66#1:211,2\n*E\n"})
public final class LyricsParser {
    @NotNull
    public static final LyricsParser INSTANCE = new LyricsParser();
    @NotNull
    private static final Pattern LINE_TIME;
    @NotNull
    private static final Pattern WORD_TIME;
    @NotNull
    private static final Pattern OFFSET;
    @NotNull
    private static final Pattern TAG;
    private static final long TAIL_MILLIS = 5000L;
    private static final long MILLIS_PER_SYLLABLE = 300L;
    private static final long MIN_SUNG_MILLIS = 1500L;
    private static final long FULL_SPAN_MILLIS = 7000L;
    @NotNull
    private static final String CYRILLIC_VOWELS = "аеёиоуыэюяіїє";
    @NotNull
    private static final String LATIN_VOWELS = "aeiouy";

    private LyricsParser() {
    }

    @JvmStatic
    @NotNull
    public static final Lyrics parse(@Nullable String content) {
        ArrayList<LyricLine> lines;
        String line;
        CharSequence charSequence = content;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return Lyrics.EMPTY;
        }
        long offset = 0L;
        Matcher offsetMatcher = OFFSET.matcher(content);
        if (offsetMatcher.find()) {
            String string = offsetMatcher.group(1);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"group(...)");
            offset = Long.parseLong(string);
        }
        ArrayList<long[]> stamps = new ArrayList<long[]>();
        ArrayList<String> bodies = new ArrayList<String>();
        ArrayList<String> plain = new ArrayList<String>();
        CharSequence charSequence2 = content;
        Regex regex = new Regex("\\R");
        int n = 0;
        for (Object raw : regex.split(charSequence2, n)) {
            line = ((Object)StringsKt.trim((CharSequence)((CharSequence)raw))).toString();
            if (((CharSequence)line).length() == 0) continue;
            Matcher matcher = LINE_TIME.matcher(line);
            int consumed = 0;
            int first = stamps.size();
            while (matcher.find(consumed) && matcher.start() == consumed) {
                long[] lArray = new long[2];
                Intrinsics.checkNotNull((Object)matcher);
                lArray[0] = INSTANCE.timestamp(matcher, 1) - offset;
                lArray[1] = bodies.size();
                stamps.add(lArray);
                consumed = matcher.end();
            }
            if (stamps.size() == first) {
                if (TAG.matcher(line).matches()) continue;
                plain.add(line);
                continue;
            }
            String string = line.substring(consumed);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            bodies.add(((Object)StringsKt.trim((CharSequence)string)).toString());
        }
        if (stamps.isEmpty()) {
            if (plain.isEmpty()) {
                return Lyrics.EMPTY;
            }
            lines = new ArrayList<LyricLine>(plain.size());
            for (String plainLine : plain) {
                lines.add(new LyricLine(0L, 0L, plainLine, CollectionsKt.emptyList()));
            }
            List<LyricLine> list = List.copyOf((Collection)lines);
            Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
            return new Lyrics(list, false);
        }
        stamps.sort(java.util.Comparator.comparingLong(a -> a[0]));
        lines = new ArrayList(stamps.size());
        int n2 = ((Collection)stamps).size();
        for (int index = 0; index < n2; ++index) {
            long start = Math.max(0L, ((long[])stamps.get(index))[0]);
            long end = index + 1 < stamps.size() ? Math.max(start, ((long[])stamps.get(index + 1))[0]) : start + 5000L;
            Object e = bodies.get((int)((long[])stamps.get(index))[1]);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            String body = (String)e;
            lines.add(WORD_TIME.matcher(body).find() ? INSTANCE.enhanced(body, start, end, offset) : INSTANCE.plainLine(body, start, end));
        }
        List<LyricLine> list = List.copyOf((Collection)lines);
        Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
        return new Lyrics(list, true);
    }

    private final LyricLine plainLine(String body, long start, long end) {
        long span = Math.max(1L, end - start);
        long natural = Math.max((long)this.syllables(body, 0, body.length()) * 300L, 1500L);
        long sung = span <= 7000L ? span : Math.min(span, natural);
        return new LyricLine(start, end, body, this.distribute(body, start, start + sung));
    }

    private final LyricLine enhanced(String body, long start, long end, long offset) {
        long[] lArray;
        StringBuilder text = new StringBuilder();
        ArrayList<LyricWord> words = new ArrayList<LyricWord>();
        ArrayList<long[]> pending = new ArrayList<long[]>();
        Matcher matcher = WORD_TIME.matcher(body);
        int cursor = 0;
        long current = -1L;
        while (matcher.find()) {
            String chunk = (String) (body.substring(cursor, matcher.start()));
            if (current >= 0L) {
                lArray = new long[]{current, text.length(), text.length() + chunk.length()};
                pending.add(lArray);
            }
            text.append(chunk);
            Intrinsics.checkNotNull((Object)matcher);
            current = Math.max(0L, this.timestamp(matcher, 1) - offset);
            cursor = matcher.end();
        }
        String string = body.substring(cursor);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        String tail = string;
        if (current >= 0L) {
            lArray = new long[]{current, text.length(), text.length() + tail.length()};
            pending.add(lArray);
        }
        text.append(tail);
        int n = ((Collection)pending).size();
        for (int index = 0; index < n; ++index) {
            int begin;
            Object e = pending.get(index);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            long[] span = (long[])e;
            long wordStart = span[0];
            long wordEnd = index + 1 < pending.size() ? Math.max(wordStart, ((long[])pending.get(index + 1))[0]) : end;
            int finish = (int)span[2];
            for (begin = (int)span[1]; begin < finish && Character.isWhitespace(text.charAt(begin)); ++begin) {
            }
            while (finish > begin && Character.isWhitespace(text.charAt(finish - 1))) {
                --finish;
            }
            if (finish <= begin) continue;
            String string2 = text.substring(begin, finish);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            words.add(new LyricWord(wordStart, wordEnd, string2, begin, finish));
        }
        String string3 = text.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toString(...)");
        List<LyricWord> list = List.copyOf((Collection)words);
        Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
        return new LyricLine(start, end, string3, list);
    }

    private final List<LyricWord> distribute(String text, long start, long end) {
        ArrayList<int[]> spans = new ArrayList<int[]>();
        int total = 0;
        int cursor = 0;
        while (cursor < text.length()) {
            while (cursor < text.length() && Character.isWhitespace(text.charAt(cursor))) {
                ++cursor;
            }
            int begin = cursor;
            while (cursor < text.length() && !Character.isWhitespace(text.charAt(cursor))) {
                ++cursor;
            }
            if (cursor <= begin) continue;
            int weight = this.syllables(text, begin, cursor);
            int[] nArray = new int[]{begin, cursor, weight};
            spans.add(nArray);
            total += weight;
        }
        if (spans.isEmpty() || total == 0) {
            return CollectionsKt.emptyList();
        }
        long duration = Math.max(0L, end - start);
        ArrayList<LyricWord> words = new ArrayList<LyricWord>(spans.size());
        long accumulated = 0L;
        Iterator iterator = spans.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            Object e = iterator2.next();
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            int[] span = (int[])e;
            long wordStart = start + duration * accumulated / (long)total;
            long wordEnd = start + duration * (accumulated += (long)span[2]) / (long)total;
            String string = text.substring(span[0], span[1]);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            words.add(new LyricWord(wordStart, wordEnd, string, span[0], span[1]));
        }
        List<LyricWord> list = List.copyOf((Collection)words);
        Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
        return list;
    }

    private final int syllables(String text, int begin, int end) {
        int count = 0;
        boolean spoken = false;
        boolean latinRun = false;
        for (int index = begin; index < end; ++index) {
            char symbol = Character.toLowerCase(text.charAt(index));
            if (Character.isLetterOrDigit(symbol)) {
                spoken = true;
            }
            if (String.valueOf(CYRILLIC_VOWELS).indexOf((char)symbol) >= 0) {
                ++count;
                latinRun = false;
                continue;
            }
            if (String.valueOf(LATIN_VOWELS).indexOf((char)symbol) >= 0) {
                if (!latinRun) {
                    ++count;
                }
                latinRun = true;
                continue;
            }
            latinRun = false;
        }
        if (count > 0) {
            return count;
        }
        return spoken ? 1 : 0;
    }

    private final long timestamp(Matcher matcher, int group) {
        String string = matcher.group(group);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"group(...)");
        long minutes = Long.parseLong(string);
        String string2 = matcher.group(group + 1);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"group(...)");
        long seconds = Long.parseLong(string2);
        String fraction = matcher.group(group + 2);
        long millis = 0L;
        if (fraction != null) {
            millis = switch (fraction.length()) {
                case 1 -> Long.parseLong(fraction) * 100L;
                case 2 -> Long.parseLong(fraction) * 10L;
                default -> Long.parseLong(fraction);
            };
        }
        return minutes * 60000L + seconds * 1000L + millis;
    }

    static {
        Pattern pattern = Pattern.compile("\\[(\\d+):(\\d{1,2})(?:[.:](\\d{1,3}))?]");
        Intrinsics.checkNotNullExpressionValue((Object)pattern, (String)"compile(...)");
        LINE_TIME = pattern;
        Pattern pattern2 = Pattern.compile("<(\\d+):(\\d{1,2})(?:[.:](\\d{1,3}))?>");
        Intrinsics.checkNotNullExpressionValue((Object)pattern2, (String)"compile(...)");
        WORD_TIME = pattern2;
        Pattern pattern3 = Pattern.compile("\\[offset:\\s*([+-]?\\d+)]", 2);
        Intrinsics.checkNotNullExpressionValue((Object)pattern3, (String)"compile(...)");
        OFFSET = pattern3;
        Pattern pattern4 = Pattern.compile("\\[[a-zA-Z#]+:.*]");
        Intrinsics.checkNotNullExpressionValue((Object)pattern4, (String)"compile(...)");
        TAG = pattern4;
    }
}

