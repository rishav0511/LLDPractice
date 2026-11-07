package org.example;

import java.util.List;

public interface PlayMode {
    Song next();
    Song previous();
    void reset(List<Song> songs);
    boolean hasNext();
    Song current();
}
