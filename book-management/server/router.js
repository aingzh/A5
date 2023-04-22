let express = require('express')
let router = express.Router()
let bus = require('./API/bus')

router.get('/bus', bus.get)

module.exports = router