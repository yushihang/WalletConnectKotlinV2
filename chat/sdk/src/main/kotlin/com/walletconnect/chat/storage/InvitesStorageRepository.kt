package com.walletconnect.chat.storage

import com.walletconnect.chat.common.model.*
import com.walletconnect.chat.storage.data.dao.InvitesQueries
import com.walletconnect.foundation.common.model.PublicKey

internal class InvitesStorageRepository(private val invites: InvitesQueries) {
    suspend fun insertInvite(invite: Invite) {
        val type = when (invite) {
            is Invite.Received -> InviteType.RECEIVED
            is Invite.Sent -> InviteType.SENT
        }

        invites.insertOrAbortInvite(
            inviteId = invite.id, message = invite.message.value, inviterAccount = invite.inviterAccount.value,
            inviteeAccount = invite.inviteeAccount.value, status = InviteStatus.PENDING, type = type,
            inviterPublicKey = invite.inviterPublicKey.keyAsHex, inviteePublicKey = invite.inviteePublicKey.keyAsHex
        )
    }

    suspend fun deleteInviteByInviteId(inviteId: Long) = invites.deleteInviteByInviteId(inviteId)

    suspend fun getReceivedInviteByInviteId(inviteId: Long) = invites.getInviteByInviteId(inviteId, ::dbToReceivedInvite).executeAsOne()

    suspend fun updateStatusByInviteId(inviteId: Long, status: InviteStatus) = invites.updateInviteStatus(status, inviteId)

    suspend fun getSentInvitesForInviterAccount(inviterAccount: String): List<Invite.Sent> = invites.getSentInvitesForInviterAccount(inviterAccount, ::dbToSentInvite).executeAsList()

    suspend fun getReceivedInvitesForInviteeAccount(inviterAccount: String): List<Invite.Received> = invites.getReceivedInvitesForInviteeAccount(inviterAccount, ::dbToReceivedInvite).executeAsList()

    private fun dbToSentInvite(
        inviteId: Long, message: String, inviterAccount: String, inviteeAccount: String,
        status: InviteStatus, inviterPublicKey: String, inviteePublicKey: String,
    ): Invite.Sent = Invite.Sent(
        id = inviteId, inviterAccount = AccountId(inviterAccount), inviteeAccount = AccountId(inviteeAccount),
        message = InviteMessage(message), inviterPublicKey = PublicKey(inviterPublicKey),
        inviteePublicKey = PublicKey(inviteePublicKey), status = status
    )

    private fun dbToReceivedInvite(
        inviteId: Long, message: String, inviterAccount: String, inviteeAccount: String,
        status: InviteStatus, inviterPublicKey: String, inviteePublicKey: String,
    ): Invite.Received = Invite.Received(
        id = inviteId, inviterAccount = AccountId(inviterAccount), inviteeAccount = AccountId(inviteeAccount),
        message = InviteMessage(message), inviterPublicKey = PublicKey(inviterPublicKey),
        inviteePublicKey = PublicKey(inviteePublicKey), status = status
    )
}
