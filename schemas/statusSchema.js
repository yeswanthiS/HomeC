const { query } = require('express-validator');

const statusSchema = [
    query('deviceId').notEmpty().withMessage('Device ID is required')
];

module.exports = statusSchema;