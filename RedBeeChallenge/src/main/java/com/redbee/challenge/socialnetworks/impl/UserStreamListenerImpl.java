package com.redbee.challenge.socialnetworks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.redbee.challenge.messages.InterestMessage;

import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

@Component
public class UserStreamListenerImpl implements UserStreamListener {

	@Autowired
	private SimpMessagingTemplate template;

	@Override
	public void onStatus(Status status) {
		System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
		InterestMessage message = new InterestMessage(status.getText());
		template.convertAndSend("/queue/interests", message);
//		String destination = "leancohn";
//		template.convertAndSendToUser(destination, "/queue/interests", message);
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
	}

	@Override
	public void onStallWarning(StallWarning warning) {
	}

	@Override
	public void onException(Exception ex) {
	}

	@Override
	public void onDeletionNotice(long directMessageId, long userId) {
	}

	@Override
	public void onFriendList(long[] friendIds) {
	}

	@Override
	public void onFavorite(User source, User target, Status favoritedStatus) {
	}

	@Override
	public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
	}

	@Override
	public void onFollow(User source, User followedUser) {
	}

	@Override
	public void onUnfollow(User source, User unfollowedUser) {
	}

	@Override
	public void onDirectMessage(DirectMessage directMessage) {
	}

	@Override
	public void onUserListMemberAddition(User addedMember, User listOwner,
			UserList list) {
	}

	@Override
	public void onUserListMemberDeletion(User deletedMember, User listOwner,
			UserList list) {
	}

	@Override
	public void onUserListSubscription(User subscriber, User listOwner,
			UserList list) {
	}

	@Override
	public void onUserListUnsubscription(User subscriber, User listOwner,
			UserList list) {
	}

	@Override
	public void onUserListCreation(User listOwner, UserList list) {
	}

	@Override
	public void onUserListUpdate(User listOwner, UserList list) {
	}

	@Override
	public void onUserListDeletion(User listOwner, UserList list) {
	}

	@Override
	public void onUserProfileUpdate(User updatedUser) {
	}

	@Override
	public void onUserSuspension(long suspendedUser) {
	}

	@Override
	public void onUserDeletion(long deletedUser) {
	}

	@Override
	public void onBlock(User source, User blockedUser) {
	}

	@Override
	public void onUnblock(User source, User unblockedUser) {
	}

	@Override
	public void onRetweetedRetweet(User source, User target,
			Status retweetedStatus) {
	}

	@Override
	public void onFavoritedRetweet(User source, User target,
			Status favoritedRetweeet) {
	}

	@Override
	public void onQuotedTweet(User source, User target, Status quotingTweet) {
	}

}
