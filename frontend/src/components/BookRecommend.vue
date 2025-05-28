<template>
    <div>
        <h2>热门推荐</h2>
        <el-table :data="books">
            <el-table-column prop="name" label="图书名称" />
            <el-table-column prop="count" label="购买次数" />
        </el-table>
    </div>
</template>
<script>
export default {
    name: 'BookRecommend',
    data() {
        return { books: [] }
    },
    mounted() {
        this.$axios.get('/api/BookRecommend').then(res => {
            this.books = res.data
        })
    }
}
</script>