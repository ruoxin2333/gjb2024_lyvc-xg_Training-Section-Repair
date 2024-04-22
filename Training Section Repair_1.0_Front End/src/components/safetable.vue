<template>
  <div>
    <!-- 安全检查记录 -->
    <div class="card" v-for="(item) in safetable" :key="item.id">
      <!-- 问题描述 -->
      <div class="card-header">
        <h2>{{ item.question }}</h2>
      </div>
      <!-- 问题图片 -->
      <div class="card-img">
        <img :src='item.pic' alt="">
        <!-- <img src='http://192.168.1.24/profile/upload/2024/03/11/7d077e25-0847-4384-b7e7-bf9027a4b3a2.jpg' /> -->
        <!-- <img src='../piccc/4.jpg' alt=""> -->
      </div>
      <div class="card-body">
        <div class="body-one">
          <!-- 姓名 -->
          <div class="body-two">上传人:</div>
          <div class="body-three">{{ item.name }}</div>
        </div>

        <div class="body-one">
          <!-- 地点 -->
          <div class="body-two">地点位置:</div>
          <div class="body-three">{{ item.place }}</div>
        </div>
        <div class="body-one">
          <!-- 联系电话 -->
          <div class="body-two">联系电话:</div>
          <div class="body-three">{{ item.phone }}</div>
        </div>
        <div class="body-one">
          <!-- 发表时间 -->
          <div class="body-two">发表时间:</div>
          <div class="body-three"> {{ item.time }}</div>
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
      safetable: [],
      page: 1,
      page_size: 4,
      total: null,
    }

  },
  created() {
    this.getsafetable();
  },
  methods: {
    // 请求安全检查记录
    getsafetable() {
      this.get(`/open/getSafe/?cur=${this.page}`
      ).then(res => {
        this.safetable = res.data.records
        this.total = res.data.total
        this.safetable.forEach(item => {
          item.pic = this.url_a + item.pic;
        })
        //  console.log(res.data);
        console.log(this.safetable);
      })
        .catch((error) => {
          console.log(error);
        })
    },
    // 分页
    handleCurrentChange(val) {
      console.log(`当前页：${val}`);
      this.page = val;
      this.getsafetable();
    },
    leftCurrentChange(val) {
      console.log(`左：${val}`);
      this.page = val;
      this.getsafetable();
    },
    rightCurrentChange(val) {
      console.log(`右：${val}`);
      this.page = val;
      this.getsafetable();
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

.body-one {
  width: 100%;
  /* border: 1px solid #000; */
  display: flex;
  justify-content: space-between;
  padding-bottom: 10px;
}

.body-two {
  width: 70px;
  /* border: 1px solid #ff4545; */
  color: #6e6c6cce;
  font-size: 15px;
}

/* 循环数据 多行隐藏 */
.body-three {
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