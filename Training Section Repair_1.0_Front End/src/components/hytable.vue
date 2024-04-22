<template>
  <div>
    <!-- 卫生检查记录 -->
    <div class="card" v-for="(item) in hytable" :key="item.id">
      <!-- 问题描述 -->
      <div class="card-header">
        <h2>{{ item.question }}</h2>
      </div>
      <!-- 问题图片 -->
      <div class="card-img">
        <img :src='item.pic' alt="">
      </div>
      <div class="card-body">
        <!-- 姓名 -->
        <div class="body-one">
          <div class="body-two">上传人:</div><div class="body-three">{{ item.name }}</div>
        </div>
        <!-- 地点 -->
        <div class="body-one">
          <div class="body-two">地点位置:</div><div class="body-three">{{ item.place }}</div>
        </div>
        <!-- 联系电话 -->
        <div class="body-one">
          <div class="body-two">联系电话:</div><div class="body-three">{{ item.phone }}</div>
        </div>
        <!-- 发表时间 -->
        <div class="body-one">
          <div class="body-two">发布时间:</div><div class="body-three">  {{ item.time }}</div>
        </div>
      </div>
    </div>
    <!-- 分页 -->
    <div class="case_paging w">
      <el-pagination style="width: fit-content;" :page-size="page_size" @current-change="handleCurrentChange"
        @prev-click="leftCurrentChange" @next-click="rightCurrentChange" layout="prev, pager, next" :total="total">
      </el-pagination>
    </div>
    <tabbar></tabbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      hytable: [],
      page: 1,
      page_size: 4,
      total: null,
    }

  },
  mounted() {
    // let token = localStorage.getItem('Authorization') || '';
    // console.log(token);
    this.gethytable();
  },
  methods: {
    // 请求安全检查记录
    gethytable() {
      this.get(
        `/api/getClean/?cur=1`,
      ).then(res => {
        // console.log(res);
        if (res) {
          this.hytable = res.data.records
          this.total = res.data.total
          this.hytable.forEach(item => {
            item.pic = this.url_a + item.pic;
          })
          // console.log("成功");
          // console.log(this.hytable);
        }
      })
        .catch((error) => {
          // console.log(error.response);
          if (error.response.data.code = 401) {
            // console.log("过期");
            localStorage.removeItem('Authorization');
            alert("如需查看卫生检查记录 请登录");
            this.$router.push({ name: 'login' })
          }
        })
    },
    // 分页
    handleCurrentChange(val) {
      console.log(`当前页：${val}`);
      this.page = val;
      this.gethytable();
    },
    leftCurrentChange(val) {
      console.log(`左：${val}`);
      this.page = val;
      this.gethytable();
    },
    rightCurrentChange(val) {
      console.log(`右：${val}`);
      this.page = val;
      this.gethytable();
    },
  }
}
</script>

<style scoped>
.card {
  border: 1px solid #ccc;
  padding: 16px;
  margin: 16px;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.12), 0 2px 10px 0 rgba(0, 0, 0, 0.16);
}
.card-body {
  text-decoration: none; 
  list-style: none;
}
.card-body {
  /* width: 100%; */
  /* border: 1px solid#000; */
  text-align: right;
  /* float: right; */
  padding: 8px 8px;
  /* margin: 16px; */
}

.card-img {
  padding: 10px;
  /* border: 1px solid #000; */
}

.card-img img {

  width: 100%;
  /* height: 100px; */
  border-radius: 8px;
}
.body-one{
  width: 100%;
  /* border: 1px solid #000; */
  display: flex;
  justify-content:space-between;
  padding-bottom: 10px;
}
.body-two{
  width: 70px;
  /* border: 1px solid #ff4545; */
  color: #6e6c6cce;
  font-size: 15px;
}
/* 循环数据 多行隐藏 */
.body-three{
  white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
}


.card-header {
  background-color: #f5f5f5;
  padding: 8px 16px;
}

.card-footer {
  background-color: #f5f5f5;
  padding: 8px 16px;
}
</style>