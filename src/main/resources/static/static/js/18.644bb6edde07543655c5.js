webpackJsonp([18],{cxj3:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=a("Dd8w"),r=a.n(s),i=a("NYxO"),n=a("+cKO"),c=a("TYx6"),l=a("i53X"),o=a("/QaM"),d={name:"NameField",props:{value:{type:String,default:""},v:{type:Object,required:!0},label:{type:String,default:"Имя"},id:{type:String,required:!0}},computed:{name:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}}},m={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"form__group",class:{fill:e.name.length>0}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.name,expression:"name"}],staticClass:"form__input",class:{invalid:e.v.$dirty&&!e.v.required||e.v.$dirty&&!e.v.minLength},attrs:{id:e.id,name:"name"},domProps:{value:e.name},on:{change:function(t){return e.v.$touch()},input:function(t){t.target.composing||(e.name=t.target.value)}}}),a("label",{staticClass:"form__label",attrs:{for:e.id}},[e._v(e._s(e.label))]),e.v.$dirty&&!e.v.required?a("span",{staticClass:"form__error"},[e._v("Обязательно поле")]):e.v.$dirty&&!e.v.minLength?a("span",{staticClass:"form__error"},[e._v("Минимальное количество символов "+e._s(e.v.minLength))]):e._e()])},staticRenderFns:[]},u=a("VU/8")(d,m,!1,null,null,null).exports,p=a("a2KH"),f={name:"CaptchaField",props:{value:{type:String,default:""},v:{type:Object,required:!0},label:{type:String,default:"Имя"},id:{type:String,required:!0}},methods:r()({},Object(i.d)("auth/api",["setFormErrors"]),{onResetCaptchaError:function(){this.setFormErrors({name:"captcha",value:!1})}}),computed:{captcha:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}}},v={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"form__group",class:{fill:e.captcha.length>0}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.captcha,expression:"captcha"}],staticClass:"form__input",class:{invalid:e.v.$dirty&&!e.v.required||!e.v.serverOk},attrs:{id:e.id,name:"captcha"},domProps:{value:e.captcha},on:{change:function(t){return e.v.$touch()},beforeinput:e.onResetCaptchaError,input:function(t){t.target.composing||(e.captcha=t.target.value)}}}),a("label",{staticClass:"form__label",attrs:{for:e.id}},[e._v(e._s(e.label))]),e.v.$dirty&&!e.v.required?a("span",{staticClass:"form__error"},[e._v("Обязательно поле")]):e.v.serverOk?e._e():a("span",{staticClass:"form__error"},[e._v("Неверно введен код с картинки")])])},staticRenderFns:[]},h=a("VU/8")(f,v,!1,null,null,null).exports,_={name:"ConfirmField",props:{value:{type:Boolean,default:""},v:{type:Object,required:!0},id:{type:String,required:!0}},computed:{confirm:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}}},g={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"form__group"},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.confirm,expression:"confirm"}],staticClass:"form__checkbox",class:{invalid:e.v.$dirty&&!e.v.sameAs},attrs:{type:"checkbox",name:"confirm",id:e.id},domProps:{checked:Array.isArray(e.confirm)?e._i(e.confirm,null)>-1:e.confirm},on:{change:function(t){var a=e.confirm,s=t.target,r=!!s.checked;if(Array.isArray(a)){var i=e._i(a,null);s.checked?i<0&&(e.confirm=a.concat([null])):i>-1&&(e.confirm=a.slice(0,i).concat(a.slice(i+1)))}else e.confirm=r}}}),a("label",{staticClass:"form__checkbox-label",attrs:{for:e.id}},[e._v("Я согласен с "),a("a",{attrs:{href:"#"}},[e._v("полит. конф-ти ")]),e._v("и передачей "),a("a",{attrs:{href:"#"}},[e._v("перс. данных")])])])},staticRenderFns:[]},b=a("VU/8")(_,g,!1,null,null,null).exports,w={name:"Registration",components:{PasswordField:c.a,EmailField:o.a,NameField:u,CaptchaField:h,NumberField:p.a,ConfirmField:b,PasswordRepeatField:l.a},data:function(){return{email:"",passwd1:"",passwd2:"",firstName:"",lastName:"",confirm:!1,captcha:""}},computed:r()({},Object(i.c)(["getCaptcha"]),Object(i.c)("auth/api",["authStatus","getFormErrors"])),methods:r()({},Object(i.b)("auth/api",["register"]),Object(i.b)(["loadCaptcha"]),{submitHandler:function(){var e=this;if(this.$v.$invalid)this.$v.$touch();else{var t=this.email,a=this.passwd1,s=this.passwd2,r=this.firstName,i=this.lastName,n=this.captcha;this.register({email:t,passwd1:a,passwd2:s,firstName:r,lastName:i,captcha:n,captcha_secret:this.getCaptcha.secret_code}).then(function(){"success"===e.authStatus&&e.$router.push({name:"RegisterSuccess"})})}}}),mounted:function(){this.loadCaptcha()},validations:{confirm:{sameAs:Object(n.sameAs)(function(){return!0})},email:{required:n.required,email:n.email,serverOk:function(){return!this.getFormErrors.email}},passwd1:{required:n.required,minLength:Object(n.minLength)(8)},passwd2:{required:n.required,minLength:Object(n.minLength)(8),sameAsPassword:Object(n.sameAs)("passwd1")},firstName:{required:n.required,minLength:Object(n.minLength)(3)},lastName:{required:n.required,minLength:Object(n.minLength)(3)},captcha:{required:n.required,serverOk:function(){return!this.getFormErrors.captcha}}}},C={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"registration"},[a("form",{staticClass:"registration__form",on:{submit:function(t){return t.preventDefault(),e.submitHandler.apply(null,arguments)}}},[a("div",{staticClass:"form__block"},[a("h4",{staticClass:"form__subtitle"},[e._v("Аккаунт")]),a("email-field",{class:{checked:e.$v.email.required&&e.$v.email.email},attrs:{id:"register-email",v:e.$v.email},model:{value:e.email,callback:function(t){e.email=t},expression:"email"}}),a("password-field",{class:{checked:e.$v.passwd1.required&&e.$v.passwd2.sameAsPassword&&e.$v.passwd1.minLength},attrs:{id:"register-password",v:e.$v.passwd1,info:"info",registration:"registration"},model:{value:e.passwd1,callback:function(t){e.passwd1=t},expression:"passwd1"}}),a("password-repeat-field",{class:{checked:e.$v.passwd1.required&&e.$v.passwd2.sameAsPassword&&e.$v.passwd2.minLength},attrs:{id:"register-repeat-password",v:e.$v.passwd2},model:{value:e.passwd2,callback:function(t){e.passwd2=t},expression:"passwd2"}})],1),a("div",{staticClass:"form__block"},[a("h4",{staticClass:"form__subtitle"},[e._v("Личные данные")]),a("name-field",{attrs:{id:"register-firstName",v:e.$v.firstName},model:{value:e.firstName,callback:function(t){e.firstName=t},expression:"firstName"}}),a("name-field",{attrs:{id:"register-lastName",v:e.$v.lastName,label:"Фамилия"},model:{value:e.lastName,callback:function(t){e.lastName=t},expression:"lastName"}})],1),a("div",{staticClass:"form__block"},[a("h4",{staticClass:"form__subtitle"},[e._v("Введите код с картинки")]),a("a",{attrs:{href:"#"},on:{click:function(t){return t.preventDefault(),e.loadCaptcha.apply(null,arguments)}}},[a("img",{staticClass:"form__code",attrs:{src:e.getCaptcha.image,alt:"Captcha"}})]),a("captcha-field",{attrs:{id:"register-captcha",v:e.$v.captcha,label:"Введите код с картинки"},model:{value:e.captcha,callback:function(t){e.captcha=t},expression:"captcha"}}),a("confirm-field",{attrs:{id:"register-confirm",v:e.$v.confirm},model:{value:e.confirm,callback:function(t){e.confirm=t},expression:"confirm"}})],1),a("div",{staticClass:"registration__action"},[a("button-hover",{attrs:{tag:"button",type:"submit",variant:"white"}},[e._v("Зарегистрироваться")])],1)])])},staticRenderFns:[]};var $=a("VU/8")(w,C,!1,function(e){a("gf6Q")},null,null);t.default=$.exports},gf6Q:function(e,t){}});
//# sourceMappingURL=18.644bb6edde07543655c5.js.map