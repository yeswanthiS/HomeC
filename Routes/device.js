const express = require('express');
const { validationResult } = require('express-validator');
const Device = require('../Models/device');
const registerSchema = require('../schemas/registerSchema');
const statusSchema = require('../schemas/statusSchema');

const router = express.Router();

// Register a new device
router.post('/register', registerSchema, async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }

    try {
        const newDevice = new Device(req.body);
        await newDevice.save();
        res.status(201).json(newDevice);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
});

// Get device status
router.get('/status', statusSchema, async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }

    try {
        const { deviceId } = req.query;
        const device = await Device.findOne({ deviceId });
        if (device) {
            res.status(200).json({
                status: device.status,
                message: `Device status is ${device.status}`
            });
        } else {
            res.status(404).json({ message: 'Device not found' });
        }
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
});

module.exports = router;