webpackJsonp([28],{MtyZ:function(a,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=n("fA45"),s=n("+Xkk"),t=n("0Hd5"),l={name:"AdminPosts",components:{AdminSidebar:s.a,AdminSearch:i.a,NewsBlock:t.a},data:function(){return{filter:"all",search:""}},methods:{onChangeFilter:function(a){this.filter=a},onChangeSearch:function(a){this.search=a}}},c={render:function(){var a=this,e=a.$createElement,n=a._self._c||e;return n("div",{staticClass:"admin-posts inner-page admin"},[n("h2",{staticClass:"admin__title"},[a._v("Публикации")]),n("div",{staticClass:"admin__wrap"},[n("div",{staticClass:"inner-page__main"},[n("div",{staticClass:"admin__search"},[n("admin-search",{on:{"change-value":a.onChangeSearch},model:{value:a.search,callback:function(e){a.search=e},expression:"search"}})],1),n("div",{staticClass:"admin-posts__list"},[n("news-block",{attrs:{admin:"admin"}}),n("news-block",{attrs:{admin:"admin",blocked:"blocked"}})],1)]),n("div",{staticClass:"inner-page__aside"},[n("admin-sidebar",{on:{"change-filter":a.onChangeFilter},model:{value:a.filter,callback:function(e){a.filter=e},expression:"filter"}})],1)])])},staticRenderFns:[]},r=n("VU/8")(l,c,!1,null,null,null);e.default=r.exports}});
//# sourceMappingURL=28.1d7cd950d49e65f472b1.js.map