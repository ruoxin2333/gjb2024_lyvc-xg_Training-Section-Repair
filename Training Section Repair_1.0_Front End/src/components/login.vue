<template>
  <div>
    <i class="el-icon-back" @click="gohome"></i>
    <el-form ref="form" :model="form" :rules="dataruler" label-width="80px">
      <h2 style="text-align: center;">登录</h2>
      <el-form-item label="账号" prop="username">
        <el-input placeholder="请输入账号" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input placeholder="请输入密码" v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-button type="primary" @click="login">登录</el-button>
    </el-form>
  </div>
</template>

<script>
import { mapMutations } from 'vuex';
export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
      }
    }
  },
  computed: {
    dataruler() {
      // (1)验证非空白字符正则
      const notnull = /\s*\S+?/
      return {
        username: [ // 存档点名称
          { required: true, message: '请输入账号', trigger: 'blur' },
          // (2)通过pattern来设置验证
          { pattern: notnull, message: '请输入非空字符', trigger: 'blur' },

        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ],
      }
    },
  },
  methods:{
    ...mapMutations(['changeLogin']),
     login() {
      let _this = this;
      this.$refs.form.validate(valid => {
        //表单验证失败
        if (!valid) {
          //提示语
          this.$message("请正确填写！");
          return false;
        }
        else {
          console.log(this.form);
          this.post("/open/login",JSON.stringify(this.form)).then(res => {
            if (res.msg = "登陆成功") {
              _this.userToken = res.token;
              console.log(_this.userToken);
              // 将用户token保存到vuex中
              localStorage.setItem('Authorization', _this.userToken);
          // _this.changeLogin({Authorization: _this.userToken });
          this.$router.push('/index');
            }
            else {
              alert("发送失败请重试");
            }
          })
        }
      });
    },
    gohome(){
      this.$router.push('/index');
    }
  }
}
</script>

<style scoped>
.el-switch.is-disabled {
  opacity: 1;
}

.el-switch.is-disabled .el-switch__core,
.el-switch.is-disabled .el-switch__label {
  cursor: pointer;
}

.el-button {
  display: block;
  margin: 0 auto;
}
</style>