<template>
  <div id="app">
    <!-- elementui  组件 使用标签页 -->
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="安全检查" name="first">
        <!-- elementui  组件 使用form表单 -->
        <!-- 安全检查表单 -->
        <el-form ref="form" :model="form" :rules="dataRuleone" label-width="80px">
          <el-form-item label="安全类型" prop="type">
            <el-select v-model="form.type" placeholder="请选择">
              <el-option v-for="item in safeoptions" :key="item.name" :label="item.name"
                :value="item.name"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="问题描述" prop="question">
            <el-input type="textarea" v-model="form.question"></el-input>
          </el-form-item>
          <el-form-item label="地点" prop="place">
            <el-select v-model="form.place" placeholder="请选择">
              <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="负责人" prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input oninput="value=value.replace(/[^\d]/g,'')" placeholder="请填写手机号码" v-model="form.phone"></el-input>
          </el-form-item>
          <!-- 上传图片 -->
          <el-form-item label="上传图片" v-model="form.pic">
            <el-upload ref="upload2" action="http://10.180.56.100:8080/open/upload" :on-preview="handlePreview2"
              :on-success="handleSuccess2" :on-error="handleError2">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">点击或拖拽文件到这里</div>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <!-- 卫生检查表单 -->
      <el-tab-pane label="卫生检查" name="second">
        <el-form ref="form2" :model="formtwo" :rules="dataRuletwo" label-width="80px">
          <el-form-item label="问题描述" prop="question">
            <el-input type="textarea" v-model="formtwo.question"></el-input>
          </el-form-item>
          <el-form-item label="记录表填写(默认为已填写)" label-width="184px" prop="status">
            <!-- 确认是否填写 @click.native="changeSwitch(formtwo.status.row)" -->
            <el-switch active-value="1" :inactive-value="0" v-model="formtwo.status"></el-switch>
          </el-form-item>
          <el-form-item label="地点" prop="place">
            <el-select v-model="formtwo.place" placeholder="请选择">
              <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="负责人" prop="name">
            <el-input v-model="formtwo.name"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input auto-complete="off" oninput="value=value.replace(/[^\d]/g,'')" placeholder="请填写手机号码"
              v-model="formtwo.phone"></el-input>
          </el-form-item>
          <!-- 上传图片 -->
          <el-form-item label="上传图片" v-model="formtwo.pic">
            <el-upload ref="upload" action="http://10.180.56.100:8080/open/upload" :on-preview="handlePreview"
              :on-success="handleSuccess" :on-error="handleError">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">点击或拖拽文件到这里</div>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">立即创建</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>


    </el-tabs>
    <tabbar></tabbar>
  </div>
</template>

<script>
// import { upload } from 'element-ui';
export default {
  // components: { upload },
  data() {
    return {
      fileList: [],
      fileList2: [],
     
      hytable:[],
     
     
      activeName: '',
      safeoptions: [
        // { value: '电类', label: '电类' },
        // { value: '墙面', label: '墙面' },
        // { value: '消防栓', label: '消防栓' },
      ],
      // optionsone: [ // 安全检查楼道选项数组

      // ],
      options: [ // 卫生检查楼道选项数组
  
      ],
      // 安全检查数组
      form: {
        // 负责人
        name: '',
        // 安全类型
        type: "",
        // 手机号
        phone: "",
        // 图片
        pic: "",
        // 地点
        place: "",
        // 问题
        question: ""
      },
      //卫生检查数组
      formtwo: {
        // 问题
        question: "",
        // 图片
        pic: "",
        // 手机号
        phone: "",
        // 负责人
        name: "",
        // 地点
        place: "",
        //实训表是否填写
        status: "1"
      },
    };
  },
  computed: {
    // dataRuleone 安全检查非空验证
    dataRuleone() {
      // (1)验证非空白字符正则
      const notnull = /\s*\S+?/
      return {
        question: [ // 存档点名称
          { required: true, message: '请输入问题描述', trigger: 'blur' },
          // (2)通过pattern来设置验证
          { pattern: notnull, message: '请输入非空字符', trigger: 'blur' },

        ],
        type: [
          { required: true, message: '请选择类型', trigger: 'change' }
        ],
        place:
          [
            { required: true, message: '请选择地址', trigger: 'change' }
          ],
        name: [
          { required: true, message: '请输入联系人', trigger: 'blur' },
          // (2)通过pattern来设置验证
          { pattern: notnull, message: '请输入非空字符', trigger: 'blur' },
          {
            validator: function (rule, value, callback) {
              if (/^[\u4e00-\u9fa5]+$/.test(value) == false) {
                callback(new Error("请输入文字"));
              } else {
                //校验通过
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        phone: [
          {
            required: true,
            message: '请输入手机号码',
            trigger: 'blur',
          },
          { min: 1, max: 11, message: '输入的号码不能超过11位', trigger: 'blur' },
          {
            validator: function (rule, value, callback) {
              if (!/^(13[0-9]|14[01,4-9]|15[0-3,5-9]|16[5-7]|17[0135678]|18[0-9]|19[189])\d{8}$/.test(value)) {
                callback(new Error('请输入标准的手机号码'))
              }
              callback()
            },
            trigger: 'blur',
          },
        ]
      }
    },
      
    // dataRuletwo卫生检查非空验证
    dataRuletwo() {
      // (1)验证非空白字符正则
      const notnull = /\s*\S+?/
      return {
        question: [ // 存档点名称
          { required: true, message: '请输入问题描述', trigger: 'blur' },
          // (2)通过pattern来设置验证
          { pattern: notnull, message: '请输入非空字符', trigger: 'blur' },

        ],
        place:
          [
            { required: true, message: '请选择', trigger: 'change' }
          ],
        name: [
          { required: true, message: '请输入联系人', trigger: 'blur' },
          // (2)通过pattern来设置验证
          { pattern: notnull, message: '请输入非空字符', trigger: 'blur' },
          {
            validator: function (rule, value, callback) {
              if (/^[\u4e00-\u9fa5]+$/.test(value) == false) {
                callback(new Error("请输入文字"));
              } else {
                //校验通过
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        phone: [
          {
            required: true,
            message: '请输入手机号码',
            trigger: 'blur',
          },
          { min: 1, max: 11, message: '输入的号码不能超过11位', trigger: 'blur' },
          {
            validator: function (rule, value, callback) {
              if (!/^(13[0-9]|14[01,4-9]|15[0-3,5-9]|16[5-7]|17[0135678]|18[0-9]|19[189])\d{8}$/.test(value)) {
                callback(new Error('请输入标准的手机号码'))
              }
              callback()
            },
            trigger: 'blur',
          },
        ]
      }
    }
  },
  methods: {
    getlogin(){
      var aa = localStorage.getItem('Authorization');
      console.log(aa);
      if(!aa){
        this.activeName="first"
      }
      else{
        this.activeName="second"
      }
    },
    // 面包屑列表详情(安全检查）
    handleClick(tab, event) {
      var token = localStorage.getItem("Authorization");
      // console.log(token);
      console.log(tab, event);
      if (tab.index == 1) {
        // console.log(1);
        if (token == null) {
          alert("如需卫生检查请先登录");
          this.$router.push({ name: 'login' })
        }
        else{
        // this.gettoken();
        }
      }
    },
    // 提交按钮(安全检查）
    onSubmit() {
      this.$refs.form.validate(valid => {
        //表单验证失败
        if (!valid) {
          //提示语
          this.$message("请正确填写！");
          return false;
        }
        else {
          console.log(this.form);
          this.post("/open/safe", JSON.stringify(this.form)).then(res => {
            if (res.msg = "添加成功") {
              console.log(res);
              alert("发送成功");
              console.log(this.form);
              this.$refs.upload2.clearFiles();
              this.$refs['form'].resetFields()
            }
            else {
              alert("发送失败请重试");
            }
          })
        }
      });
    },
    // 卫生检查 提交按钮
    submitForm() {
      //进行表单验证
      this.$refs.form2.validate(valid => {
        //表单验证失败
        if (!valid) {
          //提示语
          this.$message("请正确填写！");
          return false;
        }
        else {
          console.log(this.formtwo);
          this.post("/open/clean", JSON.stringify(this.formtwo)).then(res => {
            if (res.msg = "添加成功") {
              console.log(res);
              alert("发送成功");
              console.log(this.formtwo);
              this.$refs.upload.clearFiles();
              this.$refs['form2'].resetFields()

            }
            else {
              alert("发送失败请重试");
            }
          })
        }
      });
    },
    // 卫生检查图片预览
    handlePreview(file) {
      // 处理图片预览
      console.log('Preview:', file);
    },
    // 卫生检查图片上传成功
    handleSuccess(response) {
      // 处理上传成功的响应
      if (response.code == "0") {
        alert("上传成功!")
        this.formtwo.pic = response.fileName
        console.log(this.formtwo);
        console.log('Success:', response.fileName);
      }

    },
    // 卫生检查上传失败
    handleError(error) {
      // 处理上传错误
      alert("上传失败请重试");
      console.log('Error:', error);
    },
    // 安全检查图片预览
    handlePreview2(file) {
      // 处理图片预览
      console.log('Preview:', file);
    },
    // 安全检查图片上传成功
    handleSuccess2(response) {
      // 处理上传成功的响应
      if (response.code == "0") {
        alert("上传成功!")
        this.form.pic = response.fileName
        console.log(this.form);
        console.log('Success:', response.fileName);
      }

    },
    // 获取地址
    getplace() {
      this.get("/open/place").then(res => {
        if (res) {
          console.log(res);
          this.options = res.data
        }
        else {

        }
      })

    },
    gettype(){
      this.get("/open/classify").then(res => {
        if (res) {
          console.log(res);
          this.safeoptions = res.data
        }
        else {

        }
      })
    },
    // 安全检查上传失败
    handleError2(error) {
      // 处理上传错误
      alert("上传失败请重试");
      console.log('Error:', error);
    },

  },
  created() {
    this.gettype();
    this.getplace();
    this.getlogin();
  }
}
</script>
<style>
.btn {
  margin-right: 8px;
}
.el-switch.is-disabled {
  opacity: 1;
}

.el-switch.is-disabled .el-switch__core,
.el-switch.is-disabled .el-switch__label {
  cursor: pointer;
}
.case_paging {
  display: flex; /* flex布局 */
  justify-content: center; /* 居中对齐 */
  margin: 15px auto 26px;
}
.case_paging a,
.case_paging span {
  display: inline-block;
  zoom: 1;
  margin: 0 5px;
  vertical-align: top;
  padding: 0 10px;
  height: 30px;
  line-height: 30px;
  font-size: 14px;
  color: #444;
}
.case_paging a {
  border: 1px solid #ddd;
}
.case_paging a:hover {
  background: #47a1d9;
  color: #fff;
}
</style>