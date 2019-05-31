const mongoose=require('mongoose');

const User=mongoose.model('users',{
firstname:{type:String},
lastname:{type:String},
username:{type:String},
password:{type:String}
})
module.exports=User;