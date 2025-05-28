// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9900', // 后端端口
        changeOrigin: true
      }
    }
  }
}
