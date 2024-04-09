import {defineStore} from "pinia";

const useMaterialStore = defineStore(
    'material',
    {
        state: () => ({
            traceNameList: []
        }),
        actions: {
            // 设置当前的TraceNameList的值
            setTraceNameList(value) {
                this.traceNameList = value
            },
            getTraceNameList() {
                return this.traceNameList
            }
        }
    })

export default useMaterialStore