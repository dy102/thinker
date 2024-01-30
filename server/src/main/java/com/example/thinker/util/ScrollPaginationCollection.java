package com.example.thinker.util;

import java.util.List;

public record ScrollPaginationCollection<T>(List<T> itemsWithNextCursor,
                                            int countPerScroll) {
    public boolean isLastScroll() {
        return this.itemsWithNextCursor.size() <= countPerScroll;
    }

    public List<T> getCurrentScrollItems() {
        if (isLastScroll()) {
            return this.itemsWithNextCursor;
        }
        return this.itemsWithNextCursor.subList(0, countPerScroll);
    }

    public T getNextCursor() {
        return itemsWithNextCursor.get(countPerScroll - 1);
    }
}
