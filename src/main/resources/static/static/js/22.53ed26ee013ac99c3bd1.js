webpackJsonp([22],{KtLO:function(s,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var t=a("Dd8w"),o=a.n(t),r=a("+cKO"),i=a("TYx6"),d=a("i53X"),n=a("NYxO"),c={name:"ChangePassword",components:{PasswordField:i.a,PasswordRepeatField:d.a},data:function(){return{email:"",code:"",password:"",passwordTwo:""}},methods:o()({},Object(n.b)("profile/account",["passwordRecoverySet"]),Object(n.b)("auth/api",["logout"]),{submitHandler:function(){var s=this;this.$v.$invalid?this.$v.$touch():this.passwordRecoverySet({password:this.passwordTwo,email:this.email,code:this.code}).then(function(){s.logout(),s.$router.push({name:"ShiftPasswordSuccess"})})}}),validations:{password:{required:r.required,minLength:Object(r.minLength)(8)},passwordTwo:{required:r.required,minLength:Object(r.minLength)(8),sameAsPassword:Object(r.sameAs)("password")}},created:function(){this.code=this.$route.query.code,this.email=this.$route.query.email}},u={render:function(){var s=this,e=s.$createElement,a=s._self._c||e;return a("div",{staticClass:"change-password"},[a("h2",{staticClass:"change-password__title form__title"},[s._v("Новый пароль")]),a("form",{staticClass:"change-password__form",on:{submit:function(e){return e.preventDefault(),s.submitHandler.apply(null,arguments)}}},[a("password-field",{class:{checked:s.$v.password.required&&s.$v.passwordTwo.sameAsPassword},attrs:{id:"change-password",v:s.$v.password,info:"info",registration:"registration"},model:{value:s.password,callback:function(e){s.password=e},expression:"password"}}),a("password-repeat-field",{class:{checked:s.$v.password.required&&s.$v.passwordTwo.sameAsPassword},attrs:{id:"change-repeat-password",v:s.$v.passwordTwo},model:{value:s.passwordTwo,callback:function(e){s.passwordTwo=e},expression:"passwordTwo"}}),a("div",{staticClass:"change-password__action"},[a("button-hover",{attrs:{tag:"button",type:"submit",variant:"white"}},[s._v("Отправить")])],1)],1)])},staticRenderFns:[]};var w=a("VU/8")(c,u,!1,function(s){a("b1e8")},null,null);e.default=w.exports},b1e8:function(s,e){}});
//# sourceMappingURL=22.53ed26ee013ac99c3bd1.js.map