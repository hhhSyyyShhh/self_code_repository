<template>
    <div>
        <h2>我的订单</h2>
        <el-table :data="orders">
            <el-table-column prop="orderId" label="订单编号" />
            <el-table-column prop="status" label="状态" />
            <el-table-column prop="totalPrice" label="总金额" />
        </el-table>
    </div>
</template>
<script>
export default {
    data() {
        return { orders: [] }
    },
    mounted() {
        this.$axios.get('/api/orders').then(res => {
            this.orders = res.data
        })
    }
}
</script>