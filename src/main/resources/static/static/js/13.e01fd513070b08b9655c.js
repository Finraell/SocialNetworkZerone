webpackJsonp([13],{JTMt:function(e,t){},ZNFZ:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("Dd8w"),n=i.n(a),s=i("NYxO"),l=i("+cKO"),r=i("a2KH"),c=i("/QaM"),u=i("IcnI"),m={name:"ShiftEmail",components:{NumberField:r.a,EmailField:c.a},data:function(){return{email:"",code:0,number:""}},computed:n()({},Object(s.c)(["getCode"])),methods:n()({},Object(s.b)("profile/account",["changeEmail"]),{submitHandler:function(){var e=this;this.$v.$invalid?this.$v.$touch():this.changeEmail({email:this.email}).then(function(){e.$router.push({name:"ShiftEmailSuccess"})})}}),mounted:function(){this.code=this.getCode},validations:{email:{required:l.required,email:l.email},number:{required:l.required,numeric:l.numeric,isCode:function(e){return+e===u.a.state.code}}}},o={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"shift-email"},[i("form",{staticClass:"shift-email__form",on:{submit:function(t){return t.preventDefault(),e.submitHandler.apply(null,arguments)}}},[i("div",{staticClass:"form__block"},[i("h4",{staticClass:"form__subtitle"},[e._v("Смена почтового ящика")]),i("email-field",{class:{checked:e.$v.email.required&&e.$v.email.email},attrs:{id:"shift-email",v:e.$v.email,placeholder:"Новый почтовый ящик"},model:{value:e.email,callback:function(t){e.email=t},expression:"email"}})],1),i("div",{staticClass:"form__block"},[i("h4",{staticClass:"form__subtitle"},[e._v("Введите код")]),i("span",{staticClass:"form__code"},[e._v(e._s(e.code))]),i("number-field",{class:{checked:e.$v.number.required&&e.$v.number.isCode},attrs:{id:"shift-number",v:e.$v.number},model:{value:e.number,callback:function(t){e.number=t},expression:"number"}})],1),i("div",{staticClass:"shift-email__action"},[i("button-hover",{attrs:{tag:"button",type:"submit",variant:"white"}},[e._v("Сменить")])],1)])])},staticRenderFns:[]};var d=i("VU/8")(m,o,!1,function(e){i("JTMt")},null,null);t.default=d.exports}});
//# sourceMappingURL=13.e01fd513070b08b9655c.js.map