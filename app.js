require('dotenv').config();
const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const deviceRoutes = require('./Routes/device');

const app = express();

app.use(bodyParser.json());

// Use the device routes
app.use('/api/device', deviceRoutes);

const PORT = process.env.PORT || 3000;
mongoose.connect(`mongodb+srv://yesh:yesh@cluster0.rigar8m.mongodb.net`, {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(() => {
    console.log('Connected to MongoDB Atlas');
}).catch(err => {
    console.error('Failed to connect to MongoDB Atlas', err);
});

app.listen(PORT, () => {
    console.log((`Server is running on port ${PORT}`));
})