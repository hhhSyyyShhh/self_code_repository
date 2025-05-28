<template>
    <div>
        <h2>用户登录</h2>
        <el-form :model="form">
            <el-form-item label="用户名">
                <el-input v-model="form.username" />
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" v-model="form.password" />
            </el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="success" @click="$router.push('/UserRegister')">注册</el-button>
        </el-form>
    </div>
</template>
<script>
export default {
    data() {
        return {
            form: { username: '', password: '' }
        }
    },
    methods: {
        login() {
            this.$axios.post('/api/login', this.form).then(() => {
                this.$message.success('登录成功')
                this.$router.push('/products')
            })
        }
    }
}
</script>