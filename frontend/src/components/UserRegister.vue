<template>
    <div>
        <h2>用户注册</h2>
        <el-form :model="form">
            <el-form-item label="用户名">
                <el-input v-model="form.username" />
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" v-model="form.password" />
            </el-form-item>
            <el-form-item label="确认密码">
                <el-input type="password" v-model="form.confirm" />
            </el-form-item>
            <el-button type="success" @click="register">注册</el-button>
        </el-form>
    </div>
</template>
<script>
export default {
    name: 'UserRegister',
    data() {
        return {
            form: { username: '', password: '', confirm: '' }
        }
    },
    methods: {
        register() {
            if (this.form.password !== this.form.confirm) {
                return this.$message.error('两次密码不一致')
            }
            this.$axios.post('/api/register', this.form).then(() => {
                this.$message.success('注册成功')
                this.$router.push('/login')
            })
        }
    }
}
</script>