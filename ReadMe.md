![WalletConnect V2](/docs/walletconnect-banner.svg)

# **WalletConnect - Kotlin**

The communications protocol for web3, WalletConnect brings the ecosystem together by enabling hundreds of wallets and apps to securely connect and interact. This repository contains Kotlin implementation of
WalletConnect v2 protocols for Android applications.

####

## BOM Instructions:
To help manage compatible dependencies stay in sync, we've introduced a [BOM](https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import) to the Kotlin SDK. With this, you only need to update the BOM version to get the latest SDKs. Just add the BOM as a dependency and then list the SDKs you want to include into your project.

### example build.gradle.kts
```kotlin
dependencies {
    implementation(platform("com.walletconnect:android-bom:{BOM version}"))
    implementation("com.walletconnect:android-core")
    implementation("com.walletconnect:web3wallet")
}
```

## SDK Chart

| BOM                                                                                     | [Core SDK](core/android) | [Sign SDK](protocol/sign) | [Auth SDK](protocol/auth) | [Chat SDK](protocol/chat) | [Notify SDK](protocol/notify) | [web3wallet](product/web3wallet) | [web3modal](product/web3modal) | [WalletConnectModal](product/walletconnectmodal) |
|-----------------------------------------------------------------------------------------|--------------------------|---------------------------|---------------------------|---------------------------|:------------------------------|----------------------------------|--------------------------------|--------------------------------------------------|
| 1.31.4                                                                                  | 1.31.3                   | 2.31.3                    | 1.28.3                    | 1.0.0.beta30              | 1.3.3                         | 1.31.3                           | 1.5.3                          | 1.5.3                                            |
| 1.31.3                                                                                  | 1.31.2                   | 2.31.2                    | 1.28.2                    | 1.0.0.beta30              | 1.3.2                         | 1.31.2                           | 1.5.2                          | 1.5.2                                            |
| 1.31.1                                                                                  | 1.31.1                   | 2.31.1                    | 1.28.1                    | 1.0.0.beta26              | 1.3.1                         | 1.31.1                           | 1.5.1                          | 1.5.1                                            |
| 1.31.0<sup>[**](https://gist.github.com/TalhaAli00/7b9e1cadf19b9dc5141cd033aa4e6172)    | 1.31.0                   | 2.31.0                    | 1.28.0                    | 1.0.0.beta25              | 1.3.0                         | 1.31.0                           | 1.5.0                          | 1.5.0                                            |
| 1.30.0                                                                                  | 1.30.0                   | 2.30.0                    | 1.27.0                    | 1.0.0.beta25              | 1.2.0                         | 1.30.0                           | 1.4.0                          | 1.4.0                                            |
| 1.23.0                                                                                  | 1.28.0                   | 2.26.0                    | 1.26.0                    | 1.0.0.beta25              | 1.1.0                         | 1.21.0                           | 1.3.0                          | 1.3.0                                            |
| 1.22.2                                                                                  | 1.27.2                   | 2.25.2                    | 1.25.2                    | 1.0.0.beta25              | 1.0.2                         | 1.20.2                           | 1.2.2                          | 1.2.2                                            |
| 1.22.1                                                                                  | 1.27.0                   | 2.25.0                    | 1.25.0                    | 1.0.0.beta23              | 1.0.0                         | 1.20.0                           | 1.2.1                          | 1.2.0                                            |
| 1.22.0                                                                                  | 1.27.0                   | 2.25.0                    | 1.25.0                    | 1.0.0.beta23              | 1.0.0                         | 1.20.0                           | 1.2.0                          | 1.2.0                                            |
| 1.21.1                                                                                  | 1.26.0                   | 2.24.0                    | 1.24.0                    | 1.0.0.beta23              | 1.0.0-beta04                  | 1.19.0                           | 1.1.1                          | 1.1.1                                            |
| 1.21.0                                                                                  | 1.26.0                   | 2.24.0                    | 1.24.0                    | 1.0.0.beta23              | 1.0.0-beta04                  | 1.19.0                           | 1.1.0                          | 1.1.0                                            |
| 1.20.0                                                                                  | 1.25.0                   | 2.23.0                    | 1.23.0                    | 1.0.0.beta22              | 1.0.0-beta03                  | 1.18.0                           | 1.0.0                          | 1.0.0-beta02                                     |
| 1.19.1                                                                                  | 1.24.0                   | 2.22.0                    | 1.22.0                    | 1.0.0.beta21              | 1.0.0-beta02                  | 1.17.0                           | 1.0.0-beta02                   | 1.0.0-beta01                                     |
| 1.19.0                                                                                  | 1.24.0                   | 2.22.0                    | 1.22.0                    | 1.0.0.beta21              | 1.0.0-beta02                  | 1.17.0                           | 1.0.0-beta01                   | 1.0.0-beta01                                     |
| 1.18.0                                                                                  | 1.23.0                   | 2.21.0                    | 1.21.0                    | 1.0.0.beta20              | 1.0.0-beta01                  | 1.16.0                           | 1.0.0-alpha11                  | 1.0.0-alpha07                                    |
| 1.17.2                                                                                  | 1.22.1                   | 2.20.1                    | 1.20.1                    | 1.0.0.beta19              | 1.0.0-alpha05                 | 1.15.1                           | 1.0.0-alpha10                  | 1.0.0-alpha06                                    |
| 1.17.1                                                                                  | 1.22.0                   | 2.20.0                    | 1.20.0                    | 1.0.0.beta18              | 1.0.0-alpha04                 | 1.15.0                           | 1.0.0-alpha09                  | 1.0.0-alpha05                                    |
| 1.17.0                                                                                  | 1.22.0                   | 2.20.0                    | 1.20.0                    | 1.0.0.beta18              | 1.0.0-alpha03                 | 1.15.0                           | 1.0.0-alpha09                  | 1.0.0-alpha05                                    |
| 1.16.0                                                                                  | 1.21.0                   | 2.19.0                    | 1.19.0                    | 1.0.0.beta17              | 1.0.0-alpha02                 | 1.14.0                           |                                | 1.0.0-alpha04                                    |
| 1.15.0                                                                                  | 1.20.0                   | 2.18.0                    | 1.18.0                    | 1.0.0-beta16              |                               | 1.13.0                           |                                | 1.0.0-alpha03                                    |
| 1.14.0                                                                                  | 1.19.0                   | 2.17.0                    | 1.17.0                    | 1.0.0-beta15              |                               | 1.12.0                           |                                | 1.0.0-alpha02                                    |
| 1.13.1                                                                                  | 1.18.0                   | 2.16.0                    | 1.16.0                    | 1.0.0-beta14              |                               | 1.11.0                           |                                | 1.0.0-alpha01                                    |
| 1.12.0                                                                                  | 1.17.0                   | 2.15.0                    | 1.15.0                    | 1.0.0-beta13              |                               | 1.10.0                           |                                |                                                  |
| 1.11.1                                                                                  | 1.16.1                   | 2.14.1                    | 1.14.1                    | 1.0.0-beta12              |                               | 1.9.1                            |                                |                                                  |
| 1.11.0                                                                                  | 1.16.0                   | 2.14.0                    | 1.14.0                    | 1.0.0-beta11              |                               | 1.9.0                            |                                |                                                  |
| 1.10.0                                                                                  | 1.15.0                   | 2.13.0                    | 1.13.0                    | 1.0.0-beta10              |                               | 1.8.0                            |                                |                                                  |
| 1.9.3                                                                                   | 1.14.3                   | 2.12.3                    | 1.12.3                    | 1.0.0-beta09              |                               | 1.7.3                            |                                |                                                  |
| 1.9.2                                                                                   | 1.14.2                   | 2.12.2                    | 1.12.2                    | 1.0.0-beta08              |                               | 1.7.2                            |                                |                                                  |
| 1.9.1                                                                                   | 1.14.1                   | 2.12.1                    | 1.12.1                    | 1.0.0-beta07              |                               | 1.7.1                            |                                |                                                  |
| 1.9.0<sup>[**](https://github.com/WalletConnect/WalletConnectKotlinV2/issues/821)</sup> | 1.14.0                   | 2.12.0                    | 1.12.0                    | 1.0.0-beta06              |                               | 1.7.0                            |                                |                                                  |
| 1.8.0                                                                                   | 1.13.0                   | 2.11.0                    | 1.11.0                    | 1.0.0-beta05              |                               | 1.6.0                            |                                |                                                  |
| 1.7.0                                                                                   | 1.12.0                   | 2.10.0                    | 1.10.0                    | 1.0.0-beta04              |                               | 1.5.0                            |                                |                                                  |
| 1.6.1                                                                                   | 1.11.1                   | 2.9.1                     | 1.9.1                     | 1.0.0-beta03              |                               | 1.4.1                            |                                |                                                  |
| 1.6.0                                                                                   | 1.11.0                   | 2.9.0                     | 1.9.0                     | 1.0.0-beta02              |                               | 1.4.0                            |                                |                                                  |
| 1.5.0                                                                                   | 1.10.0                   | 2.8.0                     | 1.8.0                     | 1.0.0-beta01              |                               | 1.3.0                            |                                |                                                  |
| 1.4.1                                                                                   | 1.9.1                    | 2.7.1                     | 1.7.1                     | 1.0.0-alpha09             |                               | 1.2.1                            |                                |                                                  |
| 1.3.0                                                                                   | 1.8.0                    | 2.6.0                     | 1.6.0                     | 1.0.0-alpha07             |                               | 1.1.0                            |                                |                                                  |
| 1.2.0                                                                                   | 1.7.0                    | 2.5.0                     | 1.5.0                     | 1.0.0-alpha06             |                               | 1.0.0                            |                                |                                                  |
| 1.1.1                                                                                   | 1.6.0                    | 2.4.0                     | 1.4.0                     | 1.0.0-alpha05             |                               |                                  |                                |                                                  |
| 1.0.1                                                                                   | 1.5.0                    | 2.3.1                     | 1.3.0                     | 1.0.0-alpha04             |                               |                                  |                                |                                                  |
|                                                                                         | 1.4.0                    | 2.2.0                     | 1.2.0                     | 1.0.0-alpha03             |                               |                                  |                                |                                                  |
|                                                                                         | 1.3.0                    | 2.1.0                     | 1.1.0                     | 1.0.0-alpha02             |                               |                                  |                                |                                                  |
|                                                                                         | 1.2.0                    |                           |                           | 1.0.0-alpha01             |                               |                                  |                                |                                                  |
|                                                                                         | 1.1.0                    | 2.0.0                     | 1.0.0                     |                           |                               |                                  |                                |                                                  |
|                                                                                         | 1.0.0                    | 2.0.0-rc.5                | 1.0.0-alpha01             |                           |                               |                                  |                                |                                                  |


## License
WalletConnect v2 is released under the Apache 2.0 license. [See LICENSE](/LICENSE) for details.
