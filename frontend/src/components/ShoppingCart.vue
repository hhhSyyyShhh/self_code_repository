<template>
    <div>
        <h2>购物车</h2>
        <el-table :data="ShoppingCart" style="width: 100%">
            <el-table-column prop="name" label="商品名" />
            <el-table-column prop="price" label="价格" />
            <el-table-column prop="quantity" label="数量" />
        </el-table>
        <el-button type="success" @click="submitOrder">提交订单</el-button>
    </div>
</template>

<script>
export default {
    name: 'ShoppingCart',
    data() {
        return {
            ShoppingCart: []
        }
    },
    mounted() {
        this.$axios.get('/api/ShoppingCart').then(res => {
            this.ShoppingCart = res.data.map(p => ({
                name: p["shopping_cart.name"],
                price: p["shopping_cart.price"]
            }));
        })
    },
    methods: {
        submitOrder() {
            this.$axios.post('/api/order', this.cart).then(() => {
                this.$message.success('订单已提交')
            })
        }
    }
}
</script>