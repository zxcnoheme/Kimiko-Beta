package rtx.kimiko.utils.media;

import com.sun.jna.win32.StdCallLibrary;

public interface OptMediaLibrary extends StdCallLibrary {
    boolean GetCurrentMediaInfo(OptMediaArtist artist);
    void FreeMediaInfo(OptMediaArtist artist);
    boolean Play();
    boolean Pause();
    boolean TogglePlayPause();
    boolean SkipNext();
    boolean SkipPrevious();
    void SeekToMs(long ms);
    float GetVolume();
    boolean SetVolume(float vol);
    void Deinitialize();
}
