webpackJsonp([14],{HW6K:function(s,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r=e("Dd8w"),t=e.n(r),o=e("+cKO"),n=e("TYx6"),d=e("i53X"),i=e("NYxO"),c={name:"ChangePassword",components:{PasswordField:n.a,PasswordRepeatField:d.a},data:function(){return{password:"",passwordTwo:""}},methods:t()({},Object(i.b)("profile/account",["passwordSet"]),{submitHandler:function(){var s=this;this.$v.$invalid?this.$v.$touch():this.passwordSet(this.passwordTwo).then(function(){s.$router.push({name:"ChangePasswordSuccess"})})}}),validations:{password:{required:o.required,minLength:Object(o.minLength)(8)},passwordTwo:{required:o.required,sameAsPassword:Object(o.sameAs)("password")}}},w={render:function(){var s=this,a=s.$createElement,e=s._self._c||a;return e("div",{staticClass:"change-password"},[e("h2",{staticClass:"change-password__title form__title"},[s._v("Новый пароль")]),e("form",{staticClass:"change-password__form",on:{submit:function(a){return a.preventDefault(),s.submitHandler.apply(null,arguments)}}},[e("password-field",{class:{checked:s.$v.password.required&&s.$v.passwordTwo.sameAsPassword},attrs:{id:"change-password",v:s.$v.password,info:"info",registration:"registration"},model:{value:s.password,callback:function(a){s.password=a},expression:"password"}}),e("password-repeat-field",{class:{checked:s.$v.password.required&&s.$v.passwordTwo.sameAsPassword},attrs:{id:"change-repeat-password",v:s.$v.passwordTwo},model:{value:s.passwordTwo,callback:function(a){s.passwordTwo=a},expression:"passwordTwo"}}),e("div",{staticClass:"change-password__action"},[e("button-hover",{attrs:{tag:"button",type:"submit",variant:"white"}},[s._v("Отправить")])],1)],1)])},staticRenderFns:[]};var p=e("VU/8")(c,w,!1,function(s){e("pe/3")},null,null);a.default=p.exports},"pe/3":function(s,a){}});
//# sourceMappingURL=14.97f786327283f82e6e9d.js.map