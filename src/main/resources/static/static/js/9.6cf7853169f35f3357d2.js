webpackJsonp([9],{"7PTA":function(e,s,t){"use strict";var n=t("Dd8w"),a=t.n(n),i=t("NYxO"),o=t("bjtH"),d={name:"NewsAdd",props:{user:Boolean},components:{AddForm:o.a},data:function(){return{isOpen:!1}},computed:a()({},Object(i.c)("profile/info",["getInfo"])),methods:{openForm:function(){this.isOpen=!0},closeForm:function(){this.isOpen=!1}}},r={render:function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("div",{staticClass:"news-add",class:{open:e.isOpen},on:{click:e.openForm}},[e.isOpen?t("add-form",{on:{"submit-complete":e.closeForm}}):t("div",{staticClass:"news-add__mask"},[e.getInfo?[e.user?t("div",{staticClass:"news-add__pic"},[t("img",{attrs:{src:e.getInfo.photo,alt:e.getInfo.fullName}})]):e._e()]:e._e(),t("span",{staticClass:"news-add__placeholder"},[e._v("Поделитесь новостью...")]),t("div",{staticClass:"news-add__block photo"},[t("simple-svg",{attrs:{filepath:"/static/img/photo.svg"}})],1),t("div",{staticClass:"news-add__block add"},[t("simple-svg",{attrs:{filepath:"/static/img/add.svg"}})],1)],2)],1)},staticRenderFns:[]},l=t("VU/8")(d,r,!1,null,null,null);s.a=l.exports},"D6/I":function(e,s){},xVQV:function(e,s,t){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var n=t("Dd8w"),a=t.n(n),i=t("NYxO"),o=t("UBpT"),d=t("0Hd5"),r=t("7PTA"),l={name:"News",components:{FriendsPossible:o.a,NewsBlock:d.a,NewsAdd:r.a},computed:a()({},Object(i.c)("profile/feeds",["getFeeds","getIsShowMore"]),Object(i.c)("profile/info",["getInfo"])),methods:a()({},Object(i.b)("profile/feeds",["apiFeeds"]),Object(i.d)("profile/feeds",["setDefaultState"])),beforeRouteEnter:function(e,s,t){t(function(e){e.setDefaultState(),e.apiFeeds()})}},c={render:function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("div",{staticClass:"news inner-page"},[t("div",{staticClass:"inner-page__main"},[t("div",{staticClass:"news__add"},[t("news-add",{attrs:{user:"user"}})],1),e.getInfo?t("div",{staticClass:"news__list"},e._l(e.getFeeds,function(s){return t("news-block",{key:s.id,attrs:{info:s,edit:e.getInfo.id===s.author.id,deleted:e.getInfo.id===s.author.id}})}),1):e._e(),t("div",{staticClass:"news-block"},[t("a",{directives:[{name:"show",rawName:"v-show",value:e.getIsShowMore,expression:"getIsShowMore"}],staticClass:"news-block__more",attrs:{href:"#"},on:{click:function(s){return s.preventDefault(),e.apiFeeds.apply(null,arguments)}}},[e._v("показать еще...")])])]),t("div",{staticClass:"inner-page__aside"},[t("friends-possible")],1)])},staticRenderFns:[]};var p=t("VU/8")(l,c,!1,function(e){t("D6/I")},null,null);s.default=p.exports}});
//# sourceMappingURL=9.6cf7853169f35f3357d2.js.map