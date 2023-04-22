let mysql = require('mysql')

let db = mysql.createPool({
    host: 'localhost',     //数据库IP地址
    user: 'root',          //数据库登录账号
    password: '1234',      //数据库登录密码
    database: 'tourbooking'       //要操作的数据库
})

module.exports = db
