import axios from 'axios';
import qs from 'qs'; // 引入 qs 库用于序列化表单数据

const http = axios.create({
    baseURL: 'http://localhost:8088',
    timeout: 20000,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded' // 设置默认 Content-Type
    }
});

// 请求拦截器
http.interceptors.request.use(config => {
    if (config.method === 'post' || config.method === 'put') {
        // 如果是 POST 或 PUT 请求，则使用 qs 进行序列化
        config.data = qs.stringify(config.data);
    }
    console.log(config);
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器
http.interceptors.response.use(response => {
    return response.data;
}, error => {
    return Promise.reject(error);
});

export default http;