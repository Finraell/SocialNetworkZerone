webpackJsonp([16],{Ckeo:function(t,e){},cxc1:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a("fA45"),n=a("+Xkk"),r=a("CqtB"),s={name:"AdminModerators",components:{AdminSidebar:n.a,AdminSearch:i.a,FriendsBlock:r.a},data:function(){return{filter:"all",list:[{text:"Все",id:"all"},{text:"Модераторы",id:"moderators"},{text:"Администраторы",id:"admins"}]}},methods:{onChangeFilter:function(t){this.filter=t}}},o={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"admin-moderators inner-page admin"},[a("h2",{staticClass:"admin__title"},[t._v("Администраторы и модераторы")]),a("div",{staticClass:"admin__wrap"},[a("div",{staticClass:"inner-page__main"},[a("a",{staticClass:"admin-moderators__link",attrs:{href:"#"}},[t._v("Добавить")]),a("friends-block",{attrs:{moderator:"moderator"}}),a("friends-block",{attrs:{moderator:"moderator"}})],1),a("div",{staticClass:"inner-page__aside"},[a("admin-sidebar",{attrs:{list:t.list},on:{"change-filter":t.onChangeFilter},model:{value:t.filter,callback:function(e){t.filter=e},expression:"filter"}})],1)])])},staticRenderFns:[]};var d=a("VU/8")(s,o,!1,function(t){a("Ckeo")},null,null);e.default=d.exports}});
//# sourceMappingURL=16.4e523647dd3a6449c0c5.js.map