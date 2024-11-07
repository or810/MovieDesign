import java.util.BitSet;

public class MediaMask{

    private static final int INITIAL_CAPACITY = 9;
    private BitSet set;

    public MediaMask(MovieMedia... medias) {
        set = new BitSet(INITIAL_CAPACITY);
        for(MovieMedia media : medias)
            set(media);
    }

    public void set(MovieMedia media) {
        set.set(media.getId());
    }

    public void flip(MovieMedia media) {
        set.flip(media.getId());
    }

    public boolean isSet(MovieMedia media) {
        return set.get(media.getId());
    }

    public String toString() {
        String str = "[";
        int index = 0;
        while(index >= 0) {
            str += MovieMedia.getNameById(index) + ", ";
            index = set.nextSetBit(index + 1);
        }
        return str + "]";
    }
    
}
