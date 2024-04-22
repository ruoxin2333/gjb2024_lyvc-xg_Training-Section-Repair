const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    host: '0.0.0.0',
    // https:true,
    port: 6103,
    client: {
      webSocketURL: 'ws://0.0.0.0:6103/ws',
    },
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    // proxy: {
    //   '/api': {
    //     target: 'http://192.168.1.31',//后端接口地址
    //     changeOrigin: true,//是否允许跨越
    //     pathRewrite: {
    //       '^/api': '/api'//重写,
    //     }
    //   }
    // }
  },
  transpileDependencies: true,
  //保存时是否校验
  lintOnSave: false,
})
