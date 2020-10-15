package com.wosika.kale.unpeeklivedata

/**
 * TODO：UnPeekLiveData 的存在是为了在 "重回二级页面" 的场景下，解决 "数据倒灌" 的问题。
 * 对 "数据倒灌" 的状况不理解的小伙伴，可参考《jetpack MVVM 精讲》的解析
 *
 *
 * https://juejin.im/post/5dafc49b6fb9a04e17209922
 *
 *
 * 本类参考了官方 SingleEventLive 的非入侵设计，
 *
 *
 * TODO：并创新性地引入了 "延迟清空消息" 的设计，
 * 如此可确保：
 * 1.一条消息能被多个观察者消费
 * 2.延迟期结束，不再能够收到旧消息的推送
 * 3.并且旧消息在延迟期结束时能从内存中释放，避免内存溢出等问题
 * 4.让非入侵设计成为可能，遵循开闭原则
 *
 *
 * TODO：增加一层 ProtectedUnPeekLiveData，
 * 用于限制从 Activity/Fragment 篡改来自 "数据层" 的数据，数据层的数据务必通过唯一可信源来分发，
 * 如果这样说还不理解，详见：
 * https://xiaozhuanlan.com/topic/6719328450 和 https://xiaozhuanlan.com/topic/0168753249
 *
 *
 * Create by KunMinX at 2020/7/21
 */
class UnPeekLiveData<T> : ProtectedUnPeekLiveData<T>() {
    public override fun setValue(value: T) {
        super.setValue(value)
    }

    override fun postValue(value: T) {
        super.postValue(value)
    }

    class Builder<T> {
        /**
         * 消息的生存时长
         */
        private var eventSurvivalTime = 1000

        /**
         * 是否允许传入 null value
         */
        private var isAllowNullValue = false

        /**
         * 是否允许自动清理，默认 true
         */
        private var isAllowToClear = true
        fun setEventSurvivalTime(eventSurvivalTime: Int): Builder<T> {
            this.eventSurvivalTime = eventSurvivalTime
            return this
        }

        fun setAllowNullValue(allowNullValue: Boolean): Builder<T> {
            isAllowNullValue = allowNullValue
            return this
        }

        fun setAllowToClear(allowToClear: Boolean): Builder<T> {
            isAllowToClear = allowToClear
            return this
        }

        fun create(): UnPeekLiveData<T> {
            val liveData = UnPeekLiveData<T>()
            liveData.DELAY_TO_CLEAR_EVENT = eventSurvivalTime
            liveData.isAllowNullValue = isAllowNullValue
            liveData.isAllowToClear = isAllowToClear
            return liveData
        }
    }
}