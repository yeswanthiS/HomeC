const { check } = require('express-validator');

const registerSchema = [
    check('deviceId').notEmpty().withMessage('Device ID is required'),
    check('deviceName').notEmpty().withMessage('Device Name is required'),
    check('status').notEmpty().withMessage('Status is required'),
    check('location').notEmpty().withMessage('Location is required')
];

module.exports = registerSchema;