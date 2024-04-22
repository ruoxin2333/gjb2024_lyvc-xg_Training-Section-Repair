<template>
    <div class="tabbar">
        <div class="tab-item" v-for="item in tabs" :key="item.text" @click="selectTab(item.path)">
            <img :src="item.img" alt="">
            {{ item.text }}
        </div>
    </div>
</template>

<script>
import homeimg from '../tabbar_img/首页.png'
import safeimg from '../tabbar_img/安全记录.png'
import hyimg from '../tabbar_img/卫生记录.png'
export default {
    name: 'Tabbar',
    data() {
        return {
            tabs: [
                { text: '首页', path: '',img:homeimg},
                { text: '安全检查记录', path: 'safetable',img:safeimg },
                { text: '卫生检查记录', path: 'hytable',img:hyimg }
            ]
        };
    },
    methods: {
        selectTab(tabText) {
            this.$router.push({ path: `/${tabText}` });
            var token = localStorage.getItem("Authorization");
      // console.log(token);
      if (tabText == 'hytable') {
        // console.log(1);
        if (token == null) {
          alert("如需卫生检查请先登录");
          this.$router.push({ name: 'login' })
        }
      }
      else{
        this.$router.push({ path: `/${tabText}` });
      }
        }
    }
};
</script>

<style scoped>
/* tabbar整体样式 */
.tabbar {
    width: 100%;
    /* height: 0px; */
    position: fixed;
    bottom: 0;
    width: 100%;
    font-size: small;
    line-height: var(--footer-height);
    background: #ffffffa1;
    /* color: #fff; */
   
    display: flex;
    justify-content: space-around;
    /* border: 1px solid #000; */
}

.tab-item {
    /* width: 110px; */
    cursor:pointer;
    text-align: center;
    padding-bottom: 8px;
    
    /* border: 1px solid #f50202; */
    /* 设置 tab-item 的样式 */
}
.tab-item img{
    width: 18px;
    padding-top: 10px;

}

</style>