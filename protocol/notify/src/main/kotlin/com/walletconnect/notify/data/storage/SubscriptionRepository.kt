@file:JvmSynthetic

package com.walletconnect.notify.data.storage

import com.walletconnect.android.internal.common.model.AccountId
import com.walletconnect.android.internal.common.model.Expiry
import com.walletconnect.android.internal.common.model.RelayProtocolOptions
import com.walletconnect.foundation.common.model.PublicKey
import com.walletconnect.foundation.common.model.Topic
import com.walletconnect.notify.common.storage.data.dao.ActiveSubscriptionsQueries
import com.walletconnect.notify.common.model.EngineDO
import com.walletconnect.notify.common.storage.data.dao.RequestedSubscriptionQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SubscriptionRepository(
    private val requestedSubscriptionQueries: RequestedSubscriptionQueries,
    private val activeSubscriptionsQueries: ActiveSubscriptionsQueries,
) {

    suspend fun insertOrAbortActiveSubscription(
        account: String,
        updatedExpiry: Long,
        relayProtocol: String,
        relayData: String?,
        mapOfScope: Map<String, Pair<String, Boolean>>,
        dappGeneratedPublicKey: String,
        notifyTopic: String,
        requestedSubscriptionRequestId: Long?,
    ) = withContext(Dispatchers.IO) {
        activeSubscriptionsQueries.insertOrAbortActiveSubscribtion(account, updatedExpiry, relayProtocol, relayData, mapOfScope, dappGeneratedPublicKey, notifyTopic, requestedSubscriptionRequestId)
    }

    suspend fun updateSubscriptionScopeAndJwtByNotifyTopic(notifyTopic: String, updateScope: Map<String, Pair<String, Boolean>>, newExpiry: Long) = withContext(Dispatchers.IO) {
        activeSubscriptionsQueries.updateSubscriptionScopeAndExpiryByNotifyTopic(updateScope, newExpiry, notifyTopic)
    }

    suspend fun getActiveSubscriptionByNotifyTopic(notifyTopic: String): EngineDO.Subscription.Active? = withContext(Dispatchers.IO) {
        activeSubscriptionsQueries.getActiveSubscriptionByNotifyTopic(notifyTopic, ::toActiveSubscriptionWithoutMetadata).executeAsOneOrNull()
    }

    suspend fun getAllActiveSubscriptions(): List<EngineDO.Subscription.Active> = withContext(Dispatchers.IO) {
        activeSubscriptionsQueries.getAllActiveSubscriptions(::toActiveSubscriptionWithoutMetadata).executeAsList()
    }

    suspend fun getRequestedSubscriptionByRequestId(requestId: Long): EngineDO.Subscription.Requested? = withContext(Dispatchers.IO) {
        requestedSubscriptionQueries.getRequestedSubscriptionByRequestId(requestId, ::toRequestedSubscription).executeAsOneOrNull()
    }

    suspend fun deleteSubscriptionByNotifyTopic(notifyTopic: String) = withContext(Dispatchers.IO) {
        val requestedSubscriptionRequestId = activeSubscriptionsQueries.getActiveSubscriptionForeignRequestedSubscriptionIdByNotifyTopic(notifyTopic).executeAsOneOrNull()?.requested_subscription_id
        if (requestedSubscriptionRequestId != null) requestedSubscriptionQueries.deleteByRequestId(requestedSubscriptionRequestId)
        activeSubscriptionsQueries.deleteByNotifyTopic(notifyTopic)
    }

    @Suppress("LocalVariableName")
    private fun toActiveSubscriptionWithoutMetadata(
        account: String,
        expiry: Long,
        relay_protocol: String,
        relay_data: String?,
        map_of_scope: Map<String, Pair<String, Boolean>>,
        dapp_generated_public_key: String,
        notify_topic: String,
        requested_subscription_id: Long?,
    ): EngineDO.Subscription.Active = EngineDO.Subscription.Active(
        account = AccountId(account),
        mapOfScope = map_of_scope.map { entry -> entry.key to EngineDO.Scope.Cached(entry.key, entry.value.first, entry.value.second) }.toMap(),
        expiry = Expiry(expiry),
        relay = RelayProtocolOptions(relay_protocol, relay_data),
        dappGeneratedPublicKey = PublicKey(dapp_generated_public_key),
        notifyTopic = Topic(notify_topic),
        dappMetaData = null,
        requestedSubscriptionId = requested_subscription_id
    )


    @Suppress("LocalVariableName")
    private fun toRequestedSubscription(
        request_id: Long,
        subscribe_topic: String,
        account: String,
        map_of_scope: Map<String, Pair<String, Boolean>>,
        response_topic: String,
        expiry: Long,
    ): EngineDO.Subscription.Requested = EngineDO.Subscription.Requested(
        requestId = request_id,
        responseTopic = Topic(response_topic),
        account = AccountId(account),
        mapOfScope = map_of_scope.map { entry -> entry.key to EngineDO.Scope.Cached(entry.key, entry.value.first, entry.value.second) }.toMap(),
        expiry = Expiry(expiry),
        subscribeTopic = Topic(subscribe_topic),
    )
}