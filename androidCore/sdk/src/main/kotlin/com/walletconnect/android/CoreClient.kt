package com.walletconnect.android

import android.app.Application
import com.walletconnect.android.pairing.PairingClient
import com.walletconnect.android.pairing.PairingInterface
import com.walletconnect.android.relay.ConnectionType
import com.walletconnect.android.relay.RelayClient
import com.walletconnect.android.relay.RelayConnectionInterface

object CoreClient {
    var Pairing: PairingInterface = PairingClient
    var Relay: RelayConnectionInterface = RelayClient

    interface CoreDelegate: PairingInterface.Delegate

    fun initialize(metaData: Core.Model.AppMetaData, relayServerUrl: String, connectionType: ConnectionType, application: Application) {
        RelayClient.initialize(relayServerUrl, connectionType, application)
        PairingClient.initialize(metaData)
    }

    fun setDelegate(delegate: CoreDelegate) {
        PairingClient.setDelegate(delegate)
    }
}