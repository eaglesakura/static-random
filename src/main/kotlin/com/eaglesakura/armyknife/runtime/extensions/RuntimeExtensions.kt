package com.eaglesakura.armyknife.runtime.extensions

/**
 * Cancellation-signal function.
 * You want to cancel, this function returns "true".
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/army-knife
 */
typealias CancelCallback = () -> Boolean
