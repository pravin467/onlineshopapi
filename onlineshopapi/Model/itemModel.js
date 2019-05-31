const mongoose=require('mongoose');

const Item=mongoose.model('items',{
itemname:{type:String},
itemimagename:{type:String},
itemdescription:{type:String},
itemprice:{type:Number}
})
module.exports=Item;