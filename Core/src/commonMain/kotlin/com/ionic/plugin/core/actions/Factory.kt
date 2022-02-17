package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.Registration
import com.ionic.plugin.core.base.CallbackContext
import kotlinx.serialization.json.JsonArray
import kotlin.reflect.KClass

abstract class Factory(baseActionCallback: BaseAction.Callback, baseActionDelegate: BaseAction.Delegate) :
    Registration {
    private val actions: MutableMap<String, KClass<out BaseAction<*>>> = HashMap()
    private val baseActionCallback: BaseAction.Callback
    private val baseActionDelegate: BaseAction.Delegate

    init {
        this.baseActionCallback = baseActionCallback
        this.baseActionDelegate = baseActionDelegate
    }

    override fun registerAction(action: String, actionType: KClass<out BaseAction<*>>) {
        actions[action] = actionType
    }

    @Throws(PluginException::class)
    abstract fun createAction(action: String, args: JsonArray?, callbackContext: CallbackContext): BaseAction<*>?;

//    @Throws(PluginException::class)
//    fun createAction(action: String, args: JSONArray?, callbackContext: CallbackContext): BaseAction? {
//        val aClass: java.lang.Class<out BaseAction?> = actions[action] ?: return null
//        return try {
//            val constructor: java.lang.reflect.Constructor<out BaseAction?> =
//                aClass.getConstructor(JSONArray::class.java)
//            val baseAction: BaseAction = constructor.newInstance(args)
//            baseAction.callback = baseActionCallback
//            baseAction.delegate = baseActionDelegate
//            baseAction.callbackContext = callbackContext
//            baseAction
//        } catch (e: NoSuchMethodException) {
//            e.printStackTrace()
//            if (e is InvocationTargetException) {
//                val ite: InvocationTargetException = e as InvocationTargetException
//                val targetException: Throwable = ite.getTargetException()
//                if (targetException is PluginException) {
//                    throw targetException as PluginException
//                }
//            }
//            throw PluginException(e.message, e)
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//            if (e is InvocationTargetException) {
//                val ite: InvocationTargetException = e as InvocationTargetException
//                val targetException: Throwable = ite.getTargetException()
//                if (targetException is PluginException) {
//                    throw targetException as PluginException
//                }
//            }
//            throw PluginException(e.message, e)
//        } catch (e: InstantiationException) {
//            e.printStackTrace()
//            if (e is InvocationTargetException) {
//                val ite: InvocationTargetException = e as InvocationTargetException
//                val targetException: Throwable = ite.getTargetException()
//                if (targetException is PluginException) {
//                    throw targetException as PluginException
//                }
//            }
//            throw PluginException(e.message, e)
//        } catch (e: InvocationTargetException) {
//            e.printStackTrace()
//            if (e is InvocationTargetException) {
//                val ite: InvocationTargetException = e as InvocationTargetException
//                val targetException: Throwable = ite.getTargetException()
//                if (targetException is PluginException) {
//                    throw targetException as PluginException
//                }
//            }
//            throw PluginException(e.message, e)
//        }
//    }
//
}