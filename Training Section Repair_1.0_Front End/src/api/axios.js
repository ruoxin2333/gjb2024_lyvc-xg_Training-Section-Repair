import axios from 'axios'
// 超时时间是5秒
axios.defaults.timeout = 5000
// 允许跨域
// axios.defaults.withCredentials = true;
let token = localStorage.getItem('Authorization') || '';
let head = token ? `application/json;charset=UTF-8;token=${token}` : 'application/json;charset=UTF-8'
// Content-Type 响应头
axios.defaults.headers.post['Content-Type'] = `application/json;charset=UTF-8`
// axios.defaults.headers.post['Content-Type'] =
//   'application/x-www-form-urlencoded;charset=UTF-8'
// 基础url
// axios.defaults.baseURL = 后台接口地址;
// 旧地址
// http://192.168.1.31
// 新地址
// 192.168.1.24
// http://192.168.1.24/api/getClean/?cur=1
// console.log(token);
axios.defaults.baseURL = 'http://10.180.56.100:8080'//接口



/**
 * 封装get方法
 */
export function get(url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.defaults.headers.common['token'] = `${localStorage.getItem('Authorization')}`;
    // console.log(axios.defaults.headers.common['token']);
    axios
      .get(url, { params: params })
      .then((response) => {
        resolve(response.data)
      })
      .catch((err) => {
        reject(err)
      })
  })
}

/**
 * 封装post方法
 */
export function post(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios
      .post(url, data)
      .then((response) => {
        resolve(response.data)
      })
      .catch((err) => {
        reject(err)
      })
  })
}
