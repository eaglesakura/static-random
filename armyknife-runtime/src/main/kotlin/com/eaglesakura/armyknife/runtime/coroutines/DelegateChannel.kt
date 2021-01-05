package com.eaglesakura.armyknife.runtime.coroutines

import kotlinx.coroutines.channels.Channel

/**
 * Delegate supported channel.
 *
 * If you want to the simple use case, then replace to "RendezvousChannel<T>".
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/army-knife
 */
@Deprecated("not necessary this class, Use to 'delegate' class with Kotlin language.")
abstract class DelegateChannel<T>(
    @Suppress("MemberVisibilityCanBePrivate")
    protected val origin: Channel<T>
) : Channel<T> by origin
