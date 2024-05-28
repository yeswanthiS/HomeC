const mongoose = require('mongoose');

const deviceSchema = new mongoose.Schema({
    deviceId: { type: String, required: true },
    deviceName: { type: String, required: true },
    status: { type: String, required: true },
    location: { type: String, required: true },
    lastUpdated: { type: Date, default: Date.now }
});

const Device = mongoose.model('Device', deviceSchema);

module.exports = Device;