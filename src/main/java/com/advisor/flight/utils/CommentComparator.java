package com.advisor.flight.utils;

import java.util.Comparator;

import com.advisor.flight.entity.Comment;

public class CommentComparator implements Comparator<Comment> {

	@Override
	public int compare(Comment o1, Comment o2) {
		return o1.getUpdatedAt().after(o2.getUpdatedAt()) ? -1 : 1;
	}
}
