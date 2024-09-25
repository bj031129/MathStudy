const Mock = require('mockjs')
const list = []
for (let i = 0; i < 10; i++) {
    list.push(Mock.mock({
        'id|+1': 1,
        'name': '@cname',
        'age|18-60': 1,
        'sex|1': ['男', '女'],
        'addr': '@county(true)',
        'time': '@datetime'
    }))
}



export default [
    {
        url: '/mock/api/test', //请求地址
        method: 'get', //请求方式
        response: () => {
            return {
                code: 200,
                msg: 'ok',
                data: Math.floor(Math.random() * 30) + 1
            }
        },
    },
]
