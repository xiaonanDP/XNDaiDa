!function(e, t) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = e.document ? t(e, !0) : function(e) {
        if (!e.document)
            throw new Error("jQuery requires a window with a document");
        return t(e)
    } : t(e)
}("undefined" != typeof window ? window : this, function(e, t) {
    function n(e) {
        var t = e.length, n = oe.type(e);
        return "function" === n || oe.isWindow(e)?!1 : 1 === e.nodeType && t?!0 : "array" === n || 0 === t || "number" == typeof t && t > 0 && t - 1 in e
    }
    function r(e, t, n) {
        if (oe.isFunction(t))
            return oe.grep(e, function(e, r) {
                return !!t.call(e, r, e) !== n
            });
        if (t.nodeType)
            return oe.grep(e, function(e) {
                return e === t !== n
            });
        if ("string" == typeof t) {
            if (pe.test(t))
                return oe.filter(t, e, n);
            t = oe.filter(t, e)
        }
        return oe.grep(e, function(e) {
            return oe.inArray(e, t) >= 0 !== n
        })
    }
    function i(e, t) {
        do 
            e = e[t];
        while (e && 1 !== e.nodeType);
        return e
    }
    function o(e) {
        var t = we[e] = {};
        return oe.each(e.match(xe) || [], function(e, n) {
            t[n]=!0
        }), t
    }
    function a() {
        me.addEventListener ? (me.removeEventListener("DOMContentLoaded", s, !1), e.removeEventListener("load", s, !1)) : (me.detachEvent("onreadystatechange", s), e.detachEvent("onload", s))
    }
    function s() {
        (me.addEventListener || "load" === event.type || "complete" === me.readyState) && (a(), oe.ready())
    }
    function l(e, t, n) {
        if (void 0 === n && 1 === e.nodeType) {
            var r = "data-" + t.replace(ke, "-$1").toLowerCase();
            if (n = e.getAttribute(r), "string" == typeof n) {
                try {
                    n = "true" === n?!0 : "false" === n?!1 : "null" === n ? null : + n + "" === n?+n : Ee.test(n) ? oe.parseJSON(n) : n
                } catch (i) {}
                oe.data(e, t, n)
            } else 
                n = void 0
        }
        return n
    }
    function u(e) {
        var t;
        for (t in e)
            if (("data" !== t ||!oe.isEmptyObject(e[t])) && "toJSON" !== t)
                return !1;
        return !0
    }
    function c(e, t, n, r) {
        if (oe.acceptData(e)) {
            var i, o, a = oe.expando, s = e.nodeType, l = s ? oe.cache: e, u = s ? e[a]: e[a] && a;
            if (u && l[u] && (r || l[u].data) || void 0 !== n || "string" != typeof t)
                return u || (u = s ? e[a] = J.pop() || oe.guid++ : a), l[u] || (l[u] = s ? {} : {
                    toJSON: oe.noop
                }), ("object" == typeof t || "function" == typeof t) && (r ? l[u] = oe.extend(l[u], t) : l[u].data = oe.extend(l[u].data, t)), o = l[u], r || (o.data || (o.data = {}), o = o.data), void 0 !== n && (o[oe.camelCase(t)] = n), "string" == typeof t ? (i = o[t], null == i && (i = o[oe.camelCase(t)])) : i = o, i
        }
    }
    function d(e, t, n) {
        if (oe.acceptData(e)) {
            var r, i, o = e.nodeType, a = o ? oe.cache: e, s = o ? e[oe.expando]: oe.expando;
            if (a[s]) {
                if (t && (r = n ? a[s] : a[s].data)) {
                    oe.isArray(t) ? t = t.concat(oe.map(t, oe.camelCase)) : t in r ? t = [t] : (t = oe.camelCase(t), t = t in r ? [t] : t.split(" ")), i = t.length;
                    for (; i--;)
                        delete r[t[i]];
                    if (n?!u(r) : !oe.isEmptyObject(r)
                        )return 
                }(n || (delete a[s].data, u(a[s]))) && (o ? oe.cleanData([e], !0) : re.deleteExpando || a != a.window ? delete a[s] : a[s] = null)
            }
        }
    }
    function f() {
        return !0
    }
    function p() {
        return !1
    }
    function h() {
        try {
            return me.activeElement
        } catch (e) {}
    }
    function m(e) {
        var t = Oe.split("|"), n = e.createDocumentFragment();
        if (n.createElement)
            for (; t.length;)
                n.createElement(t.pop());
        return n
    }
    function g(e, t) {
        var n, r, i = 0, o = typeof e.getElementsByTagName !== Ne ? e.getElementsByTagName(t || "*"): typeof e.querySelectorAll !== Ne ? e.querySelectorAll(t || "*"): void 0;
        if (!o)
            for (o = [], n = e.childNodes || e; null != (r = n[i]); i++)
                !t || oe.nodeName(r, t) ? o.push(r) : oe.merge(o, g(r, t));
        return void 0 === t || t && oe.nodeName(e, t) ? oe.merge([e], o) : o
    }
    function v(e) {
        Le.test(e.type) && (e.defaultChecked = e.checked)
    }
    function y(e, t) {
        return oe.nodeName(e, "table") && oe.nodeName(11 !== t.nodeType ? t : t.firstChild, "tr") ? e.getElementsByTagName("tbody")[0] || e.appendChild(e.ownerDocument.createElement("tbody")) : e
    }
    function b(e) {
        return e.type = (null !== oe.find.attr(e, "type")) + "/" + e.type, e
    }
    function x(e) {
        var t = Je.exec(e.type);
        return t ? e.type = t[1] : e.removeAttribute("type"), e
    }
    function w(e, t) {
        for (var n, r = 0; null != (n = e[r]); r++)
            oe._data(n, "globalEval", !t || oe._data(t[r], "globalEval"))
    }
    function T(e, t) {
        if (1 === t.nodeType && oe.hasData(e)) {
            var n, r, i, o = oe._data(e), a = oe._data(t, o), s = o.events;
            if (s) {
                delete a.handle, a.events = {};
                for (n in s)
                    for (r = 0, i = s[n].length; i > r; r++)
                        oe.event.add(t, n, s[n][r])
                    }
            a.data && (a.data = oe.extend({}, a.data))
        }
    }
    function C(e, t) {
        var n, r, i;
        if (1 === t.nodeType) {
            if (n = t.nodeName.toLowerCase(), !re.noCloneEvent && t[oe.expando]) {
                i = oe._data(t);
                for (r in i.events)
                    oe.removeEvent(t, r, i.handle);
                t.removeAttribute(oe.expando)
            }
            "script" === n && t.text !== e.text ? (b(t).text = e.text, x(t)) : "object" === n ? (t.parentNode && (t.outerHTML = e.outerHTML), re.html5Clone && e.innerHTML&&!oe.trim(t.innerHTML) && (t.innerHTML = e.innerHTML)) : "input" === n && Le.test(e.type) ? (t.defaultChecked = t.checked = e.checked, t.value !== e.value && (t.value = e.value)) : "option" === n ? t.defaultSelected = t.selected = e.defaultSelected : ("input" === n || "textarea" === n) && (t.defaultValue = e.defaultValue)
        }
    }
    function N(t, n) {
        var r = oe(n.createElement(t)).appendTo(n.body), i = e.getDefaultComputedStyle ? e.getDefaultComputedStyle(r[0]).display: oe.css(r[0], "display");
        return r.detach(), i
    }
    function E(e) {
        var t = me, n = et[e];
        return n || (n = N(e, t), "none" !== n && n || (Ze = (Ze || oe("<iframe frameborder='0' width='0' height='0'/>")).appendTo(t.documentElement), t = (Ze[0].contentWindow || Ze[0].contentDocument).document, t.write(), t.close(), n = N(e, t), Ze.detach()), et[e] = n), n
    }
    function k(e, t) {
        return {
            get: function() {
                var n = e();
                return null != n ? n ? void delete this.get : (this.get = t).apply(this, arguments) : void 0
            }
        }
    }
    function S(e, t) {
        if (t in e)
            return t;
        for (var n = t.charAt(0).toUpperCase() + t.slice(1), r = t, i = pt.length; i--;)
            if (t = pt[i] + n, t in e)
                return t;
        return r
    }
    function A(e, t) {
        for (var n, r, i, o = [], a = 0, s = e.length; s > a; a++)
            r = e[a], r.style && (o[a] = oe._data(r, "olddisplay"), n = r.style.display, t ? (o[a] || "none" !== n || (r.style.display = ""), "" === r.style.display && De(r) && (o[a] = oe._data(r, "olddisplay", E(r.nodeName)))) : o[a] || (i = De(r), (n && "none" !== n ||!i) && oe._data(r, "olddisplay", i ? n : oe.css(r, "display"))));
        for (a = 0; s > a; a++)
            r = e[a], r.style && (t && "none" !== r.style.display && "" !== r.style.display || (r.style.display = t ? o[a] || "" : "none"));
        return e
    }
    function D(e, t, n) {
        var r = ut.exec(t);
        return r ? Math.max(0, r[1] - (n || 0)) + (r[2] || "px") : t
    }
    function j(e, t, n, r, i) {
        for (var o = n === (r ? "border" : "content") ? 4 : "width" === t ? 1 : 0, a = 0; 4 > o; o += 2)
            "margin" === n && (a += oe.css(e, n + Ae[o], !0, i)), r ? ("content" === n && (a -= oe.css(e, "padding" + Ae[o], !0, i)), "margin" !== n && (a -= oe.css(e, "border" + Ae[o] + "Width", !0, i))) : (a += oe.css(e, "padding" + Ae[o], !0, i), "padding" !== n && (a += oe.css(e, "border" + Ae[o] + "Width", !0, i)));
        return a
    }
    function L(e, t, n) {
        var r=!0, i = "width" === t ? e.offsetWidth : e.offsetHeight, o = tt(e), a = re.boxSizing() && "border-box" === oe.css(e, "boxSizing", !1, o);
        if (0 >= i || null == i) {
            if (i = nt(e, t, o), (0 > i || null == i) && (i = e.style[t]), it.test(i))
                return i;
            r = a && (re.boxSizingReliable() || i === e.style[t]), i = parseFloat(i) || 0
        }
        return i + j(e, t, n || (a ? "border" : "content"), r, o) + "px"
    }
    function H(e, t, n, r, i) {
        return new H.prototype.init(e, t, n, r, i)
    }
    function q() {
        return setTimeout(function() {
            ht = void 0
        }), ht = oe.now()
    }
    function _(e, t) {
        var n, r = {
            height: e
        }, i = 0;
        for (t = t ? 1 : 0; 4 > i; i += 2 - t)
            n = Ae[i], r["margin" + n] = r["padding" + n] = e;
        return t && (r.opacity = r.width = e), r
    }
    function M(e, t, n) {
        for (var r, i = (xt[t] || []).concat(xt["*"]), o = 0, a = i.length; a > o; o++)
            if (r = i[o].call(n, t, e))
                return r
    }
    function F(e, t, n) {
        var r, i, o, a, s, l, u, c, d = this, f = {}, p = e.style, h = e.nodeType && De(e), m = oe._data(e, "fxshow");
        n.queue || (s = oe._queueHooks(e, "fx"), null == s.unqueued && (s.unqueued = 0, l = s.empty.fire, s.empty.fire = function() {
            s.unqueued || l()
        }), s.unqueued++, d.always(function() {
            d.always(function() {
                s.unqueued--, oe.queue(e, "fx").length || s.empty.fire()
            })
        })), 1 === e.nodeType && ("height"in t || "width"in t) && (n.overflow = [p.overflow, p.overflowX, p.overflowY], u = oe.css(e, "display"), c = E(e.nodeName), "none" === u && (u = c), "inline" === u && "none" === oe.css(e, "float") && (re.inlineBlockNeedsLayout && "inline" !== c ? p.zoom = 1 : p.display = "inline-block")), n.overflow && (p.overflow = "hidden", re.shrinkWrapBlocks() || d.always(function() {
            p.overflow = n.overflow[0], p.overflowX = n.overflow[1], p.overflowY = n.overflow[2]
        }));
        for (r in t)
            if (i = t[r], gt.exec(i)) {
                if (delete t[r], o = o || "toggle" === i, i === (h ? "hide" : "show")) {
                    if ("show" !== i ||!m || void 0 === m[r])
                        continue;
                        h=!0
                }
                f[r] = m && m[r] || oe.style(e, r)
            }
        if (!oe.isEmptyObject(f)) {
            m ? "hidden"in m && (h = m.hidden) : m = oe._data(e, "fxshow", {}), o && (m.hidden=!h), h ? oe(e).show() : d.done(function() {
                oe(e).hide()
            }), d.done(function() {
                var t;
                oe._removeData(e, "fxshow");
                for (t in f)
                    oe.style(e, t, f[t])
            });
            for (r in f)
                a = M(h ? m[r] : 0, r, d), r in m || (m[r] = a.start, h && (a.end = a.start, a.start = "width" === r || "height" === r ? 1 : 0))
        }
    }
    function O(e, t) {
        var n, r, i, o, a;
        for (n in e)
            if (r = oe.camelCase(n), i = t[r], o = e[n], oe.isArray(o) && (i = o[1], o = e[n] = o[0]), n !== r && (e[r] = o, delete e[n]), a = oe.cssHooks[r], a && "expand"in a) {
                o = a.expand(o), delete e[r];
                for (n in o)
                    n in e || (e[n] = o[n], t[n] = i)
            } else 
                t[r] = i
    }
    function B(e, t, n) {
        var r, i, o = 0, a = bt.length, s = oe.Deferred().always(function() {
            delete l.elem
        }), l = function() {
            if (i)
                return !1;
            for (var t = ht || q(), n = Math.max(0, u.startTime + u.duration - t), r = n / u.duration || 0, o = 1 - r, a = 0, l = u.tweens.length; l > a; a++)
                u.tweens[a].run(o);
            return s.notifyWith(e, [u, o, n]), 1 > o && l ? n : (s.resolveWith(e, [u]), !1)
        }, u = s.promise({
            elem: e,
            props: oe.extend({}, t),
            opts: oe.extend(!0, {
                specialEasing: {}
            }, n),
            originalProperties: t,
            originalOptions: n,
            startTime: ht || q(),
            duration: n.duration,
            tweens: [],
            createTween: function(t, n) {
                var r = oe.Tween(e, u.opts, t, n, u.opts.specialEasing[t] || u.opts.easing);
                return u.tweens.push(r), r
            },
            stop: function(t) {
                var n = 0, r = t ? u.tweens.length: 0;
                if (i)
                    return this;
                for (i=!0; r > n; n++)
                    u.tweens[n].run(1);
                return t ? s.resolveWith(e, [u, t]) : s.rejectWith(e, [u, t]), this
            }
        }), c = u.props;
        for (O(c, u.opts.specialEasing);
        a > o;
        o++)if (r = bt[o].call(u, e, c, u.opts))
            return r;
        return oe.map(c, M, u), oe.isFunction(u.opts.start) && u.opts.start.call(e, u), oe.fx.timer(oe.extend(l, {
            elem: e,
            anim: u,
            queue: u.opts.queue
        })), u.progress(u.opts.progress).done(u.opts.done, u.opts.complete).fail(u.opts.fail).always(u.opts.always)
    }
    function P(e) {
        return function(t, n) {
            "string" != typeof t && (n = t, t = "*");
            var r, i = 0, o = t.toLowerCase().match(xe) || [];
            if (oe.isFunction(n))
                for (; r = o[i++];)
                    "+" === r.charAt(0) ? (r = r.slice(1) || "*", (e[r] = e[r] || []).unshift(n)) : (e[r] = e[r] || []).push(n)
        }
    }
    function R(e, t, n, r) {
        function i(s) {
            var l;
            return o[s]=!0, oe.each(e[s] || [], function(e, s) {
                var u = s(t, n, r);
                return "string" != typeof u || a || o[u] ? a?!(l = u) : void 0 : (t.dataTypes.unshift(u), i(u), !1)
            }), l
        }
        var o = {}, a = e === It;
        return i(t.dataTypes[0]) ||!o["*"] && i("*")
    }
    function W(e, t) {
        var n, r, i = oe.ajaxSettings.flatOptions || {};
        for (r in t)
            void 0 !== t[r] && ((i[r] ? e : n || (n = {}))[r] = t[r]);
        return n && oe.extend(!0, e, n), e
    }
    function $(e, t, n) {
        for (var r, i, o, a, s = e.contents, l = e.dataTypes; "*" === l[0];)
            l.shift(), void 0 === i && (i = e.mimeType || t.getResponseHeader("Content-Type"));
        if (i)
            for (a in s)
                if (s[a] && s[a].test(i)) {
                    l.unshift(a);
                    break
                }
        if (l[0]in n)
            o = l[0];
        else {
            for (a in n) {
                if (!l[0] || e.converters[a + " " + l[0]]) {
                    o = a;
                    break
                }
                r || (r = a)
            }
            o = o || r
        }
        return o ? (o !== l[0] && l.unshift(o), n[o]) : void 0
    }
    function z(e, t, n, r) {
        var i, o, a, s, l, u = {}, c = e.dataTypes.slice();
        if (c[1])
            for (a in e.converters)
                u[a.toLowerCase()] = e.converters[a];
        for (o = c.shift(); o;)
            if (e.responseFields[o] && (n[e.responseFields[o]] = t), !l && r && e.dataFilter && (t = e.dataFilter(t, e.dataType)), l = o, o = c.shift())
                if ("*" === o)
                    o = l;
                else if ("*" !== l && l !== o) {
                    if (a = u[l + " " + o] || u["* " + o], !a)
                        for (i in u)
                            if (s = i.split(" "), s[1] === o && (a = u[l + " " + s[0]] || u["* " + s[0]])) {
                                a===!0 ? a = u[i] : u[i]!==!0 && (o = s[0], c.unshift(s[1]));
                                break
                            }
                            if (a!==!0)
                                if (a && e["throws"])
                                    t = a(t);
                                else 
                                    try {
                                        t = a(t)
                                    } catch (d) {
                                        return {
                                            state: "parsererror",
                                            error: a ? d: "No conversion from " + l + " to " + o
                                        }
                                    }
                                }
        return {
            state: "success",
            data: t
        }
    }
    function I(e, t, n, r) {
        var i;
        if (oe.isArray(t))
            oe.each(t, function(t, i) {
                n || Jt.test(e) ? r(e, i) : I(e + "[" + ("object" == typeof i ? t : "") + "]", i, n, r)
            });
        else if (n || "object" !== oe.type(t))
            r(e, t);
        else 
            for (i in t)
                I(e + "[" + i + "]", t[i], n, r)
            }
    function X() {
        try {
            return new e.XMLHttpRequest
        } catch (t) {}
    }
    function U() {
        try {
            return new e.ActiveXObject("Microsoft.XMLHTTP")
        } catch (t) {}
    }
    function V(e) {
        return oe.isWindow(e) ? e : 9 === e.nodeType ? e.defaultView || e.parentWindow : !1
    }
    var J = [], Y = J.slice, G = J.concat, Q = J.push, K = J.indexOf, Z = {}, ee = Z.toString, te = Z.hasOwnProperty, ne = "".trim, re = {}, ie = "1.11.0", oe = function(e, t) {
        return new oe.fn.init(e, t)
    }, ae = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, se = /^-ms-/, le = /-([\da-z])/gi, ue = function(e, t) {
        return t.toUpperCase()
    };
    oe.fn = oe.prototype = {
        jquery: ie,
        constructor: oe,
        selector: "",
        length: 0,
        toArray: function() {
            return Y.call(this)
        },
        get: function(e) {
            return null != e ? 0 > e ? this[e + this.length] : this[e] : Y.call(this)
        },
        pushStack: function(e) {
            var t = oe.merge(this.constructor(), e);
            return t.prevObject = this, t.context = this.context, t
        },
        each: function(e, t) {
            return oe.each(this, e, t)
        },
        map: function(e) {
            return this.pushStack(oe.map(this, function(t, n) {
                return e.call(t, n, t)
            }))
        },
        slice: function() {
            return this.pushStack(Y.apply(this, arguments))
        },
        first: function() {
            return this.eq(0)
        },
        last: function() {
            return this.eq( - 1)
        },
        eq: function(e) {
            var t = this.length, n =+ e + (0 > e ? t : 0);
            return this.pushStack(n >= 0 && t > n ? [this[n]] : [])
        },
        end: function() {
            return this.prevObject || this.constructor(null)
        },
        push: Q,
        sort: J.sort,
        splice: J.splice
    }, oe.extend = oe.fn.extend = function() {
        var e, t, n, r, i, o, a = arguments[0] || {}, s = 1, l = arguments.length, u=!1;
        for ("boolean" == typeof a && (u = a, a = arguments[s] || {}, s++), "object" == typeof a || oe.isFunction(a) || (a = {}), s === l && (a = this, s--); l > s; s++)
            if (null != (i = arguments[s]))
                for (r in i)
                    e = a[r], n = i[r], a !== n && (u && n && (oe.isPlainObject(n) || (t = oe.isArray(n))) ? (t ? (t=!1, o = e && oe.isArray(e) ? e : []) : o = e && oe.isPlainObject(e) ? e : {}, a[r] = oe.extend(u, o, n)) : void 0 !== n && (a[r] = n));
        return a
    }, oe.extend({
        expando: "jQuery" + (ie + Math.random()).replace(/\D/g, ""),
        isReady: !0,
        error: function(e) {
            throw new Error(e)
        },
        noop: function() {},
        isFunction: function(e) {
            return "function" === oe.type(e)
        },
        isArray: Array.isArray || function(e) {
            return "array" === oe.type(e)
        },
        isWindow: function(e) {
            return null != e && e == e.window
        },
        isNumeric: function(e) {
            return e - parseFloat(e) >= 0
        },
        isEmptyObject: function(e) {
            var t;
            for (t in e)
                return !1;
            return !0
        },
        isPlainObject: function(e) {
            var t;
            if (!e || "object" !== oe.type(e) || e.nodeType || oe.isWindow(e))
                return !1;
            try {
                if (e.constructor&&!te.call(e, "constructor")&&!te.call(e.constructor.prototype, "isPrototypeOf"))
                    return !1
            } catch (n) {
                return !1
            }
            if (re.ownLast)
                for (t in e)
                    return te.call(e, t);
            for (t in e);
            return void 0 === t || te.call(e, t)
        },
        type: function(e) {
            return null == e ? e + "" : "object" == typeof e || "function" == typeof e ? Z[ee.call(e)] || "object" : typeof e
        },
        globalEval: function(t) {
            t && oe.trim(t) && (e.execScript || function(t) {
                e.eval.call(e, t)
            })(t)
        },
        camelCase: function(e) {
            return e.replace(se, "ms-").replace(le, ue)
        },
        nodeName: function(e, t) {
            return e.nodeName && e.nodeName.toLowerCase() === t.toLowerCase()
        },
        each: function(e, t, r) {
            var i, o = 0, a = e.length, s = n(e);
            if (r) {
                if (s)
                    for (; a > o && (i = t.apply(e[o], r), i!==!1); o++);
                else 
                    for (o in e)
                        if (i = t.apply(e[o], r), i===!1)
                            break
            } else if (s)
                for (; a > o && (i = t.call(e[o], o, e[o]), i!==!1); o++);
            else 
                for (o in e)
                    if (i = t.call(e[o], o, e[o]), i===!1)
                        break;
            return e
        },
        trim: ne&&!ne.call("\ufeff ") ? function(e) {
            return null == e ? "" : ne.call(e)
        }
        : function(e) {
            return null == e ? "" : (e + "").replace(ae, "")
        },
        makeArray: function(e, t) {
            var r = t || [];
            return null != e && (n(Object(e)) ? oe.merge(r, "string" == typeof e ? [e] : e) : Q.call(r, e)), r
        },
        inArray: function(e, t, n) {
            var r;
            if (t) {
                if (K)
                    return K.call(t, e, n);
                for (r = t.length, n = n ? 0 > n ? Math.max(0, r + n) : n : 0; r > n; n++)
                    if (n in t && t[n] === e)
                        return n
            }
            return - 1
        },
        merge: function(e, t) {
            for (var n =+ t.length, r = 0, i = e.length; n > r;)
                e[i++] = t[r++];
            if (n !== n)
                for (; void 0 !== t[r];)
                    e[i++] = t[r++];
            return e.length = i, e
        },
        grep: function(e, t, n) {
            for (var r, i = [], o = 0, a = e.length, s=!n; a > o; o++)
                r=!t(e[o], o), r !== s && i.push(e[o]);
            return i
        },
        map: function(e, t, r) {
            var i, o = 0, a = e.length, s = n(e), l = [];
            if (s)
                for (; a > o; o++)
                    i = t(e[o], o, r), null != i && l.push(i);
            else 
                for (o in e)
                    i = t(e[o], o, r), null != i && l.push(i);
            return G.apply([], l)
        },
        guid: 1,
        proxy: function(e, t) {
            var n, r, i;
            return "string" == typeof t && (i = e[t], t = e, e = i), oe.isFunction(e) ? (n = Y.call(arguments, 2), r = function() {
                return e.apply(t || this, n.concat(Y.call(arguments)))
            }, r.guid = e.guid = e.guid || oe.guid++, r) : void 0
        },
        now: function() {
            return + new Date
        },
        support: re
    }), oe.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function(e, t) {
        Z["[object " + t + "]"] = t.toLowerCase()
    });
    var ce = function(e) {
        function t(e, t, n, r) {
            var i, o, a, s, l, u, d, h, m, g;
            if ((t ? t.ownerDocument || t : R) !== H && L(t), t = t || H, n = n || [], !e || "string" != typeof e)
                return n;
            if (1 !== (s = t.nodeType) && 9 !== s)
                return [];
            if (_&&!r) {
                if (i = ye.exec(e))
                    if (a = i[1]) {
                        if (9 === s) {
                            if (o = t.getElementById(a), !o ||!o.parentNode)
                                return n;
                                if (o.id === a)
                                    return n.push(o), n
                        } else if (t.ownerDocument && (o = t.ownerDocument.getElementById(a)) && B(t, o) && o.id === a)
                            return n.push(o), n
                    } else {
                        if (i[2])
                            return Z.apply(n, t.getElementsByTagName(e)), n;
                            if ((a = i[3]) && C.getElementsByClassName && t.getElementsByClassName)
                                return Z.apply(n, t.getElementsByClassName(a)), n
                    }
                if (C.qsa && (!M ||!M.test(e))) {
                    if (h = d = P, m = t, g = 9 === s && e, 1 === s && "object" !== t.nodeName.toLowerCase()) {
                        for (u = f(e), (d = t.getAttribute("id")) ? h = d.replace(xe, "\\$&") : t.setAttribute("id", h), h = "[id='" + h + "'] ", l = u.length; l--;)
                            u[l] = h + p(u[l]);
                        m = be.test(e) && c(t.parentNode) || t, g = u.join(",")
                    }
                    if (g)
                        try {
                            return Z.apply(n, m.querySelectorAll(g)), n
                    } catch (v) {} finally {
                        d || t.removeAttribute("id")
                    }
                }
            }
            return w(e.replace(le, "$1"), t, n, r)
        }
        function n() {
            function e(n, r) {
                return t.push(n + " ") > N.cacheLength && delete e[t.shift()], e[n + " "] = r
            }
            var t = [];
            return e
        }
        function r(e) {
            return e[P]=!0, e
        }
        function i(e) {
            var t = H.createElement("div");
            try {
                return !!e(t)
            } catch (n) {
                return !1
            } finally {
                t.parentNode && t.parentNode.removeChild(t), t = null
            }
        }
        function o(e, t) {
            for (var n = e.split("|"), r = e.length; r--;)
                N.attrHandle[n[r]] = t
        }
        function a(e, t) {
            var n = t && e, r = n && 1 === e.nodeType && 1 === t.nodeType && (~t.sourceIndex || J) - (~e.sourceIndex || J);
            if (r)
                return r;
            if (n)
                for (; n = n.nextSibling;)
                    if (n === t)
                        return - 1;
            return e ? 1 : - 1
        }
        function s(e) {
            return function(t) {
                var n = t.nodeName.toLowerCase();
                return "input" === n && t.type === e
            }
        }
        function l(e) {
            return function(t) {
                var n = t.nodeName.toLowerCase();
                return ("input" === n || "button" === n) && t.type === e
            }
        }
        function u(e) {
            return r(function(t) {
                return t =+ t, r(function(n, r) {
                    for (var i, o = e([], n.length, t), a = o.length; a--;)
                        n[i = o[a]] && (n[i]=!(r[i] = n[i]))
                })
            })
        }
        function c(e) {
            return e && typeof e.getElementsByTagName !== V && e
        }
        function d() {}
        function f(e, n) {
            var r, i, o, a, s, l, u, c = I[e + " "];
            if (c)
                return n ? 0 : c.slice(0);
            for (s = e, l = [], u = N.preFilter; s;) {
                (!r || (i = ue.exec(s))) && (i && (s = s.slice(i[0].length) || s), l.push(o = [])), r=!1, (i = ce.exec(s)) && (r = i.shift(), o.push({
                    value: r,
                    type: i[0].replace(le, " ")
                }), s = s.slice(r.length));
                for (a in N.filter)
                    !(i = he[a].exec(s)) || u[a]&&!(i = u[a](i)) || (r = i.shift(), o.push({
                        value: r,
                        type: a,
                        matches: i
                    }), s = s.slice(r.length));
                if (!r)
                    break
            }
            return n ? s.length : s ? t.error(e) : I(e, l).slice(0)
        }
        function p(e) {
            for (var t = 0, n = e.length, r = ""; n > t; t++)
                r += e[t].value;
            return r
        }
        function h(e, t, n) {
            var r = t.dir, i = n && "parentNode" === r, o = $++;
            return t.first ? function(t, n, o) {
                for (; t = t[r];)
                    if (1 === t.nodeType || i)
                        return e(t, n, o)
            } : function(t, n, a) {
                var s, l, u = [W, o];
                if (a) {
                    for (; t = t[r];)
                        if ((1 === t.nodeType || i) && e(t, n, a))
                            return !0
                } else 
                    for (; t = t[r];)
                        if (1 === t.nodeType || i) {
                            if (l = t[P] || (t[P] = {}), (s = l[r]) && s[0] === W && s[1] === o)
                                return u[2] = s[2];
                                if (l[r] = u, u[2] = e(t, n, a))
                                    return !0
                        }
            }
        }
        function m(e) {
            return e.length > 1 ? function(t, n, r) {
                for (var i = e.length; i--;)
                    if (!e[i](t, n, r))
                        return !1;
                return !0
            } : e[0]
        }
        function g(e, t, n, r, i) {
            for (var o, a = [], s = 0, l = e.length, u = null != t; l > s; s++)(o = e[s]) 
                && (!n || n(o, r, i)) && (a.push(o), u && t.push(s));
            return a
        }
        function v(e, t, n, i, o, a) {
            return i&&!i[P] && (i = v(i)), o&&!o[P] && (o = v(o, a)), r(function(r, a, s, l) {
                var u, c, d, f = [], p = [], h = a.length, m = r || x(t || "*", s.nodeType ? [s] : s, []), v=!e ||!r && t ? m : g(m, f, e, s, l), y = n ? o || (r ? e : h || i) ? [] : a : v;
                if (n && n(v, y, s, l), i)
                    for (u = g(y, p), i(u, [], s, l), c = u.length; c--;)(d = u[c]) 
                        && (y[p[c]]=!(v[p[c]] = d));
                if (r) {
                    if (o || e) {
                        if (o) {
                            for (u = [], c = y.length; c--;)(d = y[c]) 
                                && u.push(v[c] = d);
                            o(null, y = [], u, l)
                        }
                        for (c = y.length; c--;)(d = y[c]) 
                            && (u = o ? te.call(r, d) : f[c])>-1 && (r[u]=!(a[u] = d))
                        }
                } else 
                    y = g(y === a ? y.splice(h, y.length) : y), o ? o(null, a, y, l) : Z.apply(a, y)
            })
        }
        function y(e) {
            for (var t, n, r, i = e.length, o = N.relative[e[0].type], a = o || N.relative[" "], s = o ? 1 : 0, l = h(function(e) {
                return e === t
            }, a, !0), u = h(function(e) {
                return te.call(t, e)>-1
            }, a, !0), c = [function(e, n, r) {
                return !o && (r || n !== A) || ((t = n).nodeType ? l(e, n, r) : u(e, n, r))
            }
            ]; i > s; s++)
                if (n = N.relative[e[s].type])
                    c = [h(m(c), n)];
                else {
                    if (n = N.filter[e[s].type].apply(null, e[s].matches), n[P]) {
                        for (r=++s; i > r&&!N.relative[e[r].type]; r++);
                        return v(s > 1 && m(c), s > 1 && p(e.slice(0, s - 1).concat({
                            value: " " === e[s - 2].type ? "*": ""
                        })).replace(le, "$1"), n, r > s && y(e.slice(s, r)), i > r && y(e = e.slice(r)), i > r && p(e))
                    }
                    c.push(n)
                }
            return m(c)
        }
        function b(e, n) {
            var i = n.length > 0, o = e.length > 0, a = function(r, a, s, l, u) {
                var c, d, f, p = 0, h = "0", m = r && [], v = [], y = A, b = r || o && N.find.TAG("*", u), x = W += null == y ? 1: Math.random() || .1, w = b.length;
                for (u && (A = a !== H && a); h !== w && null != (c = b[h]); h++) {
                    if (o && c) {
                        for (d = 0; f = e[d++];)
                            if (f(c, a, s)) {
                                l.push(c);
                                break
                            }
                        u && (W = x)
                    }
                    i && ((c=!f && c) && p--, r && m.push(c))
                }
                if (p += h, i && h !== p) {
                    for (d = 0; f = n[d++];)
                        f(m, v, a, s);
                    if (r) {
                        if (p > 0)
                            for (; h--;)
                                m[h] || v[h] || (v[h] = Q.call(l));
                        v = g(v)
                    }
                    Z.apply(l, v), u&&!r && v.length > 0 && p + n.length > 1 && t.uniqueSort(l)
                }
                return u && (W = x, A = y), m
            };
            return i ? r(a) : a
        }
        function x(e, n, r) {
            for (var i = 0, o = n.length; o > i; i++)
                t(e, n[i], r);
            return r
        }
        function w(e, t, n, r) {
            var i, o, a, s, l, u = f(e);
            if (!r && 1 === u.length) {
                if (o = u[0] = u[0].slice(0), o.length > 2 && "ID" === (a = o[0]).type && C.getById && 9 === t.nodeType && _ && N.relative[o[1].type]) {
                    if (t = (N.find.ID(a.matches[0].replace(we, Te), t) || [])[0], !t)
                        return n;
                    e = e.slice(o.shift().value.length)
                }
                for (i = he.needsContext.test(e) ? 0 : o.length; i--&&(a = o[i], !N.relative[s = a.type]);)
                    if ((l = N.find[s]) && (r = l(a.matches[0].replace(we, Te), be.test(o[0].type) && c(t.parentNode) || t))) {
                        if (o.splice(i, 1), e = r.length && p(o), !e)
                            return Z.apply(n, r), n;
                            break
                    }
            }
            return S(e, u)(r, t, !_, n, be.test(e) && c(t.parentNode) || t), n
        }
        var T, C, N, E, k, S, A, D, j, L, H, q, _, M, F, O, B, P = "sizzle" +- new Date, R = e.document, W = 0, $ = 0, z = n(), I = n(), X = n(), U = function(e, t) {
            return e === t && (j=!0), 0
        }, V = "undefined", J = 1<<31, Y = {}.hasOwnProperty, G = [], Q = G.pop, K = G.push, Z = G.push, ee = G.slice, te = G.indexOf || function(e) {
            for (var t = 0, n = this.length; n > t; t++)
                if (this[t] === e)
                    return t;
            return - 1
        }, ne = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped", re = "[\\x20\\t\\r\\n\\f]", ie = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+", oe = ie.replace("w", "w#"), ae = "\\[" + re + "*(" + ie + ")" + re + "*(?:([*^$|!~]?=)" + re + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + oe + ")|)|)" + re + "*\\]", se = ":(" + ie + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + ae.replace(3, 8) + ")*)|.*)\\)|)", le = new RegExp("^" + re + "+|((?:^|[^\\\\])(?:\\\\.)*)" + re + "+$", "g"), ue = new RegExp("^" + re + "*," + re + "*"), ce = new RegExp("^" + re + "*([>+~]|" + re + ")" + re + "*"), de = new RegExp("=" + re + "*([^\\]'\"]*?)" + re + "*\\]", "g"), fe = new RegExp(se), pe = new RegExp("^" + oe + "$"), he = {
            ID: new RegExp("^#(" + ie + ")"),
            CLASS: new RegExp("^\\.(" + ie + ")"),
            TAG: new RegExp("^(" + ie.replace("w", "w*") + ")"),
            ATTR: new RegExp("^" + ae),
            PSEUDO: new RegExp("^" + se),
            CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + re + "*(even|odd|(([+-]|)(\\d*)n|)" + re + "*(?:([+-]|)" + re + "*(\\d+)|))" + re + "*\\)|)", "i"),
            bool: new RegExp("^(?:" + ne + ")$", "i"),
            needsContext: new RegExp("^" + re + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + re + "*((?:-\\d)?\\d*)" + re + "*\\)|)(?=[^-]|$)", "i")
        }, me = /^(?:input|select|textarea|button)$/i, ge = /^h\d$/i, ve = /^[^{]+\{\s*\[native \w/, ye = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, be = /[+~]/, xe = /'|\\/g, we = new RegExp("\\\\([\\da-f]{1,6}" + re + "?|(" + re + ")|.)", "ig"), Te = function(e, t, n) {
            var r = "0x" + t - 65536;
            return r !== r || n ? t : 0 > r ? String.fromCharCode(r + 65536) : String.fromCharCode(r>>10 | 55296, 1023 & r | 56320)
        };
        try {
            Z.apply(G = ee.call(R.childNodes), R.childNodes), G[R.childNodes.length].nodeType
        } catch (Ce) {
            Z = {
                apply: G.length ? function(e, t) {
                    K.apply(e, ee.call(t))
                }
                : function(e, t) {
                    for (var n = e.length, r = 0; e[n++] = t[r++];);
                    e.length = n - 1
                }
            }
        }
        C = t.support = {}, k = t.isXML = function(e) {
            var t = e && (e.ownerDocument || e).documentElement;
            return t ? "HTML" !== t.nodeName : !1
        }, L = t.setDocument = function(e) {
            var t, n = e ? e.ownerDocument || e: R, r = n.defaultView;
            return n !== H && 9 === n.nodeType && n.documentElement ? (H = n, q = n.documentElement, _=!k(n), r && r !== r.top && (r.addEventListener ? r.addEventListener("unload", function() {
                L()
            }, !1) : r.attachEvent && r.attachEvent("onunload", function() {
                L()
            })), C.attributes = i(function(e) {
                return e.className = "i", !e.getAttribute("className")
            }), C.getElementsByTagName = i(function(e) {
                return e.appendChild(n.createComment("")), !e.getElementsByTagName("*").length
            }), C.getElementsByClassName = ve.test(n.getElementsByClassName) && i(function(e) {
                return e.innerHTML = "<div class='a'></div><div class='a i'></div>", e.firstChild.className = "i", 2 === e.getElementsByClassName("i").length
            }), C.getById = i(function(e) {
                return q.appendChild(e).id = P, !n.getElementsByName ||!n.getElementsByName(P).length
            }), C.getById ? (N.find.ID = function(e, t) {
                if (typeof t.getElementById !== V && _) {
                    var n = t.getElementById(e);
                    return n && n.parentNode ? [n] : []
                }
            }, N.filter.ID = function(e) {
                var t = e.replace(we, Te);
                return function(e) {
                    return e.getAttribute("id") === t
                }
            }) : (delete N.find.ID, N.filter.ID = function(e) {
                var t = e.replace(we, Te);
                return function(e) {
                    var n = typeof e.getAttributeNode !== V && e.getAttributeNode("id");
                    return n && n.value === t
                }
            }), N.find.TAG = C.getElementsByTagName ? function(e, t) {
                return typeof t.getElementsByTagName !== V ? t.getElementsByTagName(e) : void 0
            } : function(e, t) {
                var n, r = [], i = 0, o = t.getElementsByTagName(e);
                if ("*" === e) {
                    for (; n = o[i++];)
                        1 === n.nodeType && r.push(n);
                    return r
                }
                return o
            }, N.find.CLASS = C.getElementsByClassName && function(e, t) {
                return typeof t.getElementsByClassName !== V && _ ? t.getElementsByClassName(e) : void 0
            }, F = [], M = [], (C.qsa = ve.test(n.querySelectorAll)) && (i(function(e) {
                e.innerHTML = "<select t=''><option selected=''></option></select>", e.querySelectorAll("[t^='']").length && M.push("[*^$]=" + re + "*(?:''|\"\")"), e.querySelectorAll("[selected]").length || M.push("\\[" + re + "*(?:value|" + ne + ")"), e.querySelectorAll(":checked").length || M.push(":checked")
            }), i(function(e) {
                var t = n.createElement("input");
                t.setAttribute("type", "hidden"), e.appendChild(t).setAttribute("name", "D"), e.querySelectorAll("[name=d]").length && M.push("name" + re + "*[*^$|!~]?="), e.querySelectorAll(":enabled").length || M.push(":enabled", ":disabled"), e.querySelectorAll("*,:x"), M.push(",.*:")
            })), (C.matchesSelector = ve.test(O = q.webkitMatchesSelector || q.mozMatchesSelector || q.oMatchesSelector || q.msMatchesSelector)) && i(function(e) {
                C.disconnectedMatch = O.call(e, "div"), O.call(e, "[s!='']:x"), F.push("!=", se)
            }), M = M.length && new RegExp(M.join("|")), F = F.length && new RegExp(F.join("|")), t = ve.test(q.compareDocumentPosition), B = t || ve.test(q.contains) ? function(e, t) {
                var n = 9 === e.nodeType ? e.documentElement: e, r = t && t.parentNode;
                return e === r ||!(!r || 1 !== r.nodeType ||!(n.contains ? n.contains(r) : e.compareDocumentPosition && 16 & e.compareDocumentPosition(r)))
            } : function(e, t) {
                if (t)
                    for (; t = t.parentNode;)
                        if (t === e)
                            return !0;
                return !1
            }, U = t ? function(e, t) {
                if (e === t)
                    return j=!0, 0;
                var r=!e.compareDocumentPosition-!t.compareDocumentPosition;
                return r ? r : (r = (e.ownerDocument || e) === (t.ownerDocument || t) ? e.compareDocumentPosition(t) : 1, 1 & r ||!C.sortDetached && t.compareDocumentPosition(e) === r ? e === n || e.ownerDocument === R && B(R, e)?-1 : t === n || t.ownerDocument === R && B(R, t) ? 1 : D ? te.call(D, e) - te.call(D, t) : 0 : 4 & r?-1 : 1)
            } : function(e, t) {
                if (e === t)
                    return j=!0, 0;
                var r, i = 0, o = e.parentNode, s = t.parentNode, l = [e], u = [t];
                if (!o ||!s)
                    return e === n?-1 : t === n ? 1 : o?-1 : s ? 1 : D ? te.call(D, e) - te.call(D, t) : 0;
                if (o === s)
                    return a(e, t);
                for (r = e; r = r.parentNode;)
                    l.unshift(r);
                for (r = t; r = r.parentNode;)
                    u.unshift(r);
                for (; l[i] === u[i];)
                    i++;
                return i ? a(l[i], u[i]) : l[i] === R?-1 : u[i] === R ? 1 : 0
            }, n) : H
        }, t.matches = function(e, n) {
            return t(e, null, null, n)
        }, t.matchesSelector = function(e, n) {
            if ((e.ownerDocument || e) !== H && L(e), n = n.replace(de, "='$1']"), !(!C.matchesSelector ||!_ || F && F.test(n) || M && M.test(n)))
                try {
                    var r = O.call(e, n);
                    if (r || C.disconnectedMatch || e.document && 11 !== e.document.nodeType)
                        return r
            } catch (i) {}
            return t(n, H, null, [e]).length > 0
        }, t.contains = function(e, t) {
            return (e.ownerDocument || e) !== H && L(e), B(e, t)
        }, t.attr = function(e, t) {
            (e.ownerDocument || e) !== H && L(e);
            var n = N.attrHandle[t.toLowerCase()], r = n && Y.call(N.attrHandle, t.toLowerCase()) ? n(e, t, !_): void 0;
            return void 0 !== r ? r : C.attributes ||!_ ? e.getAttribute(t) : (r = e.getAttributeNode(t)) && r.specified ? r.value : null
        }, t.error = function(e) {
            throw new Error("Syntax error, unrecognized expression: " + e)
        }, t.uniqueSort = function(e) {
            var t, n = [], r = 0, i = 0;
            if (j=!C.detectDuplicates, D=!C.sortStable && e.slice(0), e.sort(U), j) {
                for (; t = e[i++];)
                    t === e[i] && (r = n.push(i));
                for (; r--;)
                    e.splice(n[r], 1)
            }
            return D = null, e
        }, E = t.getText = function(e) {
            var t, n = "", r = 0, i = e.nodeType;
            if (i) {
                if (1 === i || 9 === i || 11 === i) {
                    if ("string" == typeof e.textContent)
                        return e.textContent;
                    for (e = e.firstChild; e; e = e.nextSibling)
                        n += E(e)
                    } else if (3 === i || 4 === i)
                    return e.nodeValue
            } else 
                for (; t = e[r++];)
                    n += E(t);
            return n
        }, N = t.selectors = {
            cacheLength: 50,
            createPseudo: r,
            match: he,
            attrHandle: {},
            find: {},
            relative: {
                ">": {
                    dir: "parentNode",
                    first: !0
                },
                " ": {
                    dir: "parentNode"
                },
                "+": {
                    dir: "previousSibling",
                    first: !0
                },
                "~": {
                    dir: "previousSibling"
                }
            },
            preFilter: {
                ATTR: function(e) {
                    return e[1] = e[1].replace(we, Te), e[3] = (e[4] || e[5] || "").replace(we, Te), "~=" === e[2] && (e[3] = " " + e[3] + " "), e.slice(0, 4)
                },
                CHILD: function(e) {
                    return e[1] = e[1].toLowerCase(), "nth" === e[1].slice(0, 3) ? (e[3] || t.error(e[0]), e[4] =+ (e[4] ? e[5] + (e[6] || 1) : 2 * ("even" === e[3] || "odd" === e[3])), e[5] =+ (e[7] + e[8] || "odd" === e[3])) : e[3] && t.error(e[0]), e
                },
                PSEUDO: function(e) {
                    var t, n=!e[5] && e[2];
                    return he.CHILD.test(e[0]) ? null : (e[3] && void 0 !== e[4] ? e[2] = e[4] : n && fe.test(n) && (t = f(n, !0)) && (t = n.indexOf(")", n.length - t) - n.length) && (e[0] = e[0].slice(0, t), e[2] = n.slice(0, t)), e.slice(0, 3))
                }
            },
            filter: {
                TAG: function(e) {
                    var t = e.replace(we, Te).toLowerCase();
                    return "*" === e ? function() {
                        return !0
                    } : function(e) {
                        return e.nodeName && e.nodeName.toLowerCase() === t
                    }
                },
                CLASS: function(e) {
                    var t = z[e + " "];
                    return t || (t = new RegExp("(^|" + re + ")" + e + "(" + re + "|$)")) && z(e, function(e) {
                        return t.test("string" == typeof e.className && e.className || typeof e.getAttribute !== V && e.getAttribute("class") || "")
                    })
                },
                ATTR: function(e, n, r) {
                    return function(i) {
                        var o = t.attr(i, e);
                        return null == o ? "!=" === n : n ? (o += "", "=" === n ? o === r : "!=" === n ? o !== r : "^=" === n ? r && 0 === o.indexOf(r) : "*=" === n ? r && o.indexOf(r)>-1 : "$=" === n ? r && o.slice( - r.length) === r : "~=" === n ? (" " + o + " ").indexOf(r)>-1 : "|=" === n ? o === r || o.slice(0, r.length + 1) === r + "-" : !1) : !0
                    }
                },
                CHILD: function(e, t, n, r, i) {
                    var o = "nth" !== e.slice(0, 3), a = "last" !== e.slice( - 4), s = "of-type" === t;
                    return 1 === r && 0 === i ? function(e) {
                        return !!e.parentNode
                    } : function(t, n, l) {
                        var u, c, d, f, p, h, m = o !== a ? "nextSibling": "previousSibling", g = t.parentNode, v = s && t.nodeName.toLowerCase(), y=!l&&!s;
                        if (g) {
                            if (o) {
                                for (; m;) {
                                    for (d = t; d = d[m];)
                                        if (s ? d.nodeName.toLowerCase() === v : 1 === d.nodeType)
                                            return !1;
                                    h = m = "only" === e&&!h && "nextSibling"
                                }
                                return !0
                            }
                            if (h = [a ? g.firstChild: g.lastChild], a && y) {
                                for (c = g[P] || (g[P] = {}), u = c[e] || [], p = u[0] === W && u[1], f = u[0] === W && u[2], d = p && g.childNodes[p]; d=++p && d && d[m] || (f = p = 0) 
                                    || h.pop();
                                )if (1 === d.nodeType&&++f && d === t) {
                                    c[e] = [W, p, f];
                                    break
                                }
                            } else if (y && (u = (t[P] || (t[P] = {}))[e]) && u[0] === W)
                                f = u[1];
                            else 
                                for (; (d=++p && d && d[m] || (f = p = 0) || h.pop()) 
                                    && ((s ? d.nodeName.toLowerCase() !== v : 1 !== d.nodeType)||!++f || (y && ((d[P] || (d[P] = {}))[e] = [W, f]), d !== t));
                            );
                            return f -= i, f === r || f%r === 0 && f / r >= 0
                        }
                    }
                },
                PSEUDO: function(e, n) {
                    var i, o = N.pseudos[e] || N.setFilters[e.toLowerCase()] || t.error("unsupported pseudo: " + e);
                    return o[P] ? o(n) : o.length > 1 ? (i = [e, e, "", n], N.setFilters.hasOwnProperty(e.toLowerCase()) ? r(function(e, t) {
                        for (var r, i = o(e, n), a = i.length; a--;)
                            r = te.call(e, i[a]), e[r]=!(t[r] = i[a])
                    }) : function(e) {
                        return o(e, 0, i)
                    }) : o
                }
            },
            pseudos: {
                not: r(function(e) {
                    var t = [], n = [], i = S(e.replace(le, "$1"));
                    return i[P] ? r(function(e, t, n, r) {
                        for (var o, a = i(e, null, r, []), s = e.length; s--;)(o = a[s]) 
                            && (e[s]=!(t[s] = o))
                    }) : function(e, r, o) {
                        return t[0] = e, i(t, null, o, n), !n.pop()
                    }
                }),
                has: r(function(e) {
                    return function(n) {
                        return t(e, n).length > 0
                    }
                }),
                contains: r(function(e) {
                    return function(t) {
                        return (t.textContent || t.innerText || E(t)).indexOf(e)>-1
                    }
                }),
                lang: r(function(e) {
                    return pe.test(e || "") || t.error("unsupported lang: " + e), e = e.replace(we, Te).toLowerCase(), function(t) {
                        var n;
                        do 
                            if (n = _ ? t.lang : t.getAttribute("xml:lang") || t.getAttribute("lang"))
                                return n = n.toLowerCase(), n === e || 0 === n.indexOf(e + "-");
                        while ((t = t.parentNode) && 1 === t.nodeType);
                        return !1
                    }
                }),
                target: function(t) {
                    var n = e.location && e.location.hash;
                    return n && n.slice(1) === t.id
                },
                root: function(e) {
                    return e === q
                },
                focus: function(e) {
                    return e === H.activeElement && (!H.hasFocus || H.hasFocus())&&!!(e.type || e.href||~e.tabIndex)
                },
                enabled: function(e) {
                    return e.disabled===!1
                },
                disabled: function(e) {
                    return e.disabled===!0
                },
                checked: function(e) {
                    var t = e.nodeName.toLowerCase();
                    return "input" === t&&!!e.checked || "option" === t&&!!e.selected
                },
                selected: function(e) {
                    return e.parentNode && e.parentNode.selectedIndex, e.selected===!0
                },
                empty: function(e) {
                    for (e = e.firstChild; e; e = e.nextSibling)
                        if (e.nodeType < 6)
                            return !1;
                    return !0
                },
                parent: function(e) {
                    return !N.pseudos.empty(e)
                },
                header: function(e) {
                    return ge.test(e.nodeName)
                },
                input: function(e) {
                    return me.test(e.nodeName)
                },
                button: function(e) {
                    var t = e.nodeName.toLowerCase();
                    return "input" === t && "button" === e.type || "button" === t
                },
                text: function(e) {
                    var t;
                    return "input" === e.nodeName.toLowerCase() && "text" === e.type && (null == (t = e.getAttribute("type")) || "text" === t.toLowerCase())
                },
                first: u(function() {
                    return [0]
                }),
                last: u(function(e, t) {
                    return [t - 1]
                }),
                eq: u(function(e, t, n) {
                    return [0 > n ? n + t: n]
                }),
                even: u(function(e, t) {
                    for (var n = 0; t > n; n += 2)
                        e.push(n);
                    return e
                }),
                odd: u(function(e, t) {
                    for (var n = 1; t > n; n += 2)
                        e.push(n);
                    return e
                }),
                lt: u(function(e, t, n) {
                    for (var r = 0 > n ? n + t : n; --r >= 0;)
                        e.push(r);
                    return e
                }),
                gt: u(function(e, t, n) {
                    for (var r = 0 > n ? n + t : n; ++r < t;)
                        e.push(r);
                    return e
                })
            }
        }, N.pseudos.nth = N.pseudos.eq;
        for (T in{
            radio: !0,
            checkbox: !0,
            file: !0,
            password: !0,
            image: !0
        })
            N.pseudos[T] = s(T);
        for (T in{
            submit: !0,
            reset: !0
        })
            N.pseudos[T] = l(T);
        return d.prototype = N.filters = N.pseudos, N.setFilters = new d, S = t.compile = function(e, t) {
            var n, r = [], i = [], o = X[e + " "];
            if (!o) {
                for (t || (t = f(e)), n = t.length; n--;)
                    o = y(t[n]), o[P] ? r.push(o) : i.push(o);
                o = X(e, b(i, r))
            }
            return o
        }, C.sortStable = P.split("").sort(U).join("") === P, C.detectDuplicates=!!j, L(), C.sortDetached = i(function(e) {
            return 1 & e.compareDocumentPosition(H.createElement("div"))
        }), i(function(e) {
            return e.innerHTML = "<a href='#'></a>", "#" === e.firstChild.getAttribute("href")
        }) || o("type|href|height|width", function(e, t, n) {
            return n ? void 0 : e.getAttribute(t, "type" === t.toLowerCase() ? 1 : 2)
        }), C.attributes && i(function(e) {
            return e.innerHTML = "<input/>", e.firstChild.setAttribute("value", ""), "" === e.firstChild.getAttribute("value")
        }) || o("value", function(e, t, n) {
            return n || "input" !== e.nodeName.toLowerCase() ? void 0 : e.defaultValue
        }), i(function(e) {
            return null == e.getAttribute("disabled")
        }) || o(ne, function(e, t, n) {
            var r;
            return n ? void 0 : e[t]===!0 ? t.toLowerCase() : (r = e.getAttributeNode(t)) && r.specified ? r.value : null
        }), t
    }(e);
    oe.find = ce, oe.expr = ce.selectors, oe.expr[":"] = oe.expr.pseudos, oe.unique = ce.uniqueSort, oe.text = ce.getText, oe.isXMLDoc = ce.isXML, oe.contains = ce.contains;
    var de = oe.expr.match.needsContext, fe = /^<(\w+)\s*\/?>(?:<\/\1>|)$/, pe = /^.[^:#\[\.,]*$/;
    oe.filter = function(e, t, n) {
        var r = t[0];
        return n && (e = ":not(" + e + ")"), 1 === t.length && 1 === r.nodeType ? oe.find.matchesSelector(r, e) ? [r] : [] : oe.find.matches(e, oe.grep(t, function(e) {
            return 1 === e.nodeType
        }))
    }, oe.fn.extend({
        find: function(e) {
            var t, n = [], r = this, i = r.length;
            if ("string" != typeof e)
                return this.pushStack(oe(e).filter(function() {
                    for (t = 0; i > t; t++)
                        if (oe.contains(r[t], this))
                            return !0
                        }));
            for (t = 0; i > t; t++)
                oe.find(e, r[t], n);
            return n = this.pushStack(i > 1 ? oe.unique(n) : n), n.selector = this.selector ? this.selector + " " + e : e, n
        },
        filter: function(e) {
            return this.pushStack(r(this, e || [], !1))
        },
        not: function(e) {
            return this.pushStack(r(this, e || [], !0))
        },
        is: function(e) {
            return !!r(this, "string" == typeof e && de.test(e) ? oe(e) : e || [], !1).length
        }
    });
    var he, me = e.document, ge = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/, ve = oe.fn.init = function(e, t) {
        var n, r;
        if (!e)
            return this;
        if ("string" == typeof e) {
            if (n = "<" === e.charAt(0) && ">" === e.charAt(e.length - 1) && e.length >= 3 ? [null, e, null] : ge.exec(e), !n ||!n[1] && t)
                return !t || t.jquery ? (t || he).find(e) : this.constructor(t).find(e);
            if (n[1]) {
                if (t = t instanceof oe ? t[0] : t, oe.merge(this, oe.parseHTML(n[1], t && t.nodeType ? t.ownerDocument || t : me, !0)), fe.test(n[1]) && oe.isPlainObject(t))
                    for (n in t)
                        oe.isFunction(this[n]) ? this[n](t[n]) : this.attr(n, t[n]);
                return this
            }
            if (r = me.getElementById(n[2]), r && r.parentNode) {
                if (r.id !== n[2])
                    return he.find(e);
                this.length = 1, this[0] = r
            }
            return this.context = me, this.selector = e, this
        }
        return e.nodeType ? (this.context = this[0] = e, this.length = 1, this) : oe.isFunction(e) ? "undefined" != typeof he.ready ? he.ready(e) : e(oe) : (void 0 !== e.selector && (this.selector = e.selector, this.context = e.context), oe.makeArray(e, this))
    };
    ve.prototype = oe.fn, he = oe(me);
    var ye = /^(?:parents|prev(?:Until|All))/, be = {
        children: !0,
        contents: !0,
        next: !0,
        prev: !0
    };
    oe.extend({
        dir: function(e, t, n) {
            for (var r = [], i = e[t]; i && 9 !== i.nodeType && (void 0 === n || 1 !== i.nodeType ||!oe(i).is(n));
            )1 === i.nodeType && r.push(i), i = i[t];
            return r
        },
        sibling: function(e, t) {
            for (var n = []; e; e = e.nextSibling)
                1 === e.nodeType && e !== t && n.push(e);
            return n
        }
    }), oe.fn.extend({
        has: function(e) {
            var t, n = oe(e, this), r = n.length;
            return this.filter(function() {
                for (t = 0; r > t; t++)
                    if (oe.contains(this, n[t]))
                        return !0
            })
        },
        closest: function(e, t) {
            for (var n, r = 0, i = this.length, o = [], a = de.test(e) || "string" != typeof e ? oe(e, t || this.context) : 0; i > r; r++)
                for (n = this[r]; n && n !== t; n = n.parentNode)
                    if (n.nodeType < 11 && (a ? a.index(n)>-1 : 1 === n.nodeType && oe.find.matchesSelector(n, e))) {
                        o.push(n);
                        break
                    }
            return this.pushStack(o.length > 1 ? oe.unique(o) : o)
        },
        index: function(e) {
            return e ? "string" == typeof e ? oe.inArray(this[0], oe(e)) : oe.inArray(e.jquery ? e[0] : e, this) : this[0] && this[0].parentNode ? this.first().prevAll().length : - 1
        },
        add: function(e, t) {
            return this.pushStack(oe.unique(oe.merge(this.get(), oe(e, t))))
        },
        addBack: function(e) {
            return this.add(null == e ? this.prevObject : this.prevObject.filter(e))
        }
    }), oe.each({
        parent: function(e) {
            var t = e.parentNode;
            return t && 11 !== t.nodeType ? t : null
        },
        parents: function(e) {
            return oe.dir(e, "parentNode")
        },
        parentsUntil: function(e, t, n) {
            return oe.dir(e, "parentNode", n)
        },
        next: function(e) {
            return i(e, "nextSibling")
        },
        prev: function(e) {
            return i(e, "previousSibling")
        },
        nextAll: function(e) {
            return oe.dir(e, "nextSibling")
        },
        prevAll: function(e) {
            return oe.dir(e, "previousSibling")
        },
        nextUntil: function(e, t, n) {
            return oe.dir(e, "nextSibling", n)
        },
        prevUntil: function(e, t, n) {
            return oe.dir(e, "previousSibling", n)
        },
        siblings: function(e) {
            return oe.sibling((e.parentNode || {}).firstChild, e)
        },
        children: function(e) {
            return oe.sibling(e.firstChild)
        },
        contents: function(e) {
            return oe.nodeName(e, "iframe") ? e.contentDocument || e.contentWindow.document : oe.merge([], e.childNodes)
        }
    }, function(e, t) {
        oe.fn[e] = function(n, r) {
            var i = oe.map(this, t, n);
            return "Until" !== e.slice( - 5) && (r = n), r && "string" == typeof r && (i = oe.filter(r, i)), this.length > 1 && (be[e] || (i = oe.unique(i)), ye.test(e) && (i = i.reverse())), this.pushStack(i)
        }
    });
    var xe = /\S+/g, we = {};
    oe.Callbacks = function(e) {
        e = "string" == typeof e ? we[e] || o(e) : oe.extend({}, e);
        var t, n, r, i, a, s, l = [], u=!e.once && [], c = function(o) {
            for (n = e.memory && o, r=!0, a = s || 0, s = 0, i = l.length, t=!0; l && i > a; a++)
                if (l[a].apply(o[0], o[1])===!1 && e.stopOnFalse) {
                    n=!1;
                    break
                }
            t=!1, l && (u ? u.length && c(u.shift()) : n ? l = [] : d.disable())
        }, d = {
            add: function() {
                if (l) {
                    var r = l.length;
                    !function o(t) {
                        oe.each(t, function(t, n) {
                            var r = oe.type(n);
                            "function" === r ? e.unique && d.has(n) || l.push(n) : n && n.length && "string" !== r && o(n)
                        })
                    }(arguments), t ? i = l.length : n && (s = r, c(n))
                }
                return this
            },
            remove: function() {
                return l && oe.each(arguments, function(e, n) {
                    for (var r; (r = oe.inArray(n, l, r))>-1;)
                        l.splice(r, 1), t && (i >= r && i--, a >= r && a--)
                }), this
            },
            has: function(e) {
                return e ? oe.inArray(e, l)>-1 : !(!l ||!l.length)
            },
            empty: function() {
                return l = [], i = 0, this
            },
            disable: function() {
                return l = u = n = void 0, this
            },
            disabled: function() {
                return !l
            },
            lock: function() {
                return u = void 0, n || d.disable(), this
            },
            locked: function() {
                return !u
            },
            fireWith: function(e, n) {
                return !l || r&&!u || (n = n || [], n = [e, n.slice ? n.slice(): n], t ? u.push(n) : c(n)), this
            },
            fire: function() {
                return d.fireWith(this, arguments), this
            },
            fired: function() {
                return !!r
            }
        };
        return d
    }, oe.extend({
        Deferred: function(e) {
            var t = [["resolve", "done", oe.Callbacks("once memory"), "resolved"], ["reject", "fail", oe.Callbacks("once memory"), "rejected"], ["notify", "progress", oe.Callbacks("memory")]], n = "pending", r = {
                state: function() {
                    return n
                },
                always: function() {
                    return i.done(arguments).fail(arguments), this
                },
                then: function() {
                    var e = arguments;
                    return oe.Deferred(function(n) {
                        oe.each(t, function(t, o) {
                            var a = oe.isFunction(e[t]) && e[t];
                            i[o[1]](function() {
                                var e = a && a.apply(this, arguments);
                                e && oe.isFunction(e.promise) ? e.promise().done(n.resolve).fail(n.reject).progress(n.notify) : n[o[0] + "With"](this === r ? n.promise() : this, a ? [e] : arguments)
                            })
                        }), e = null
                    }).promise()
                },
                promise: function(e) {
                    return null != e ? oe.extend(e, r) : r
                }
            }, i = {};
            return r.pipe = r.then, oe.each(t, function(e, o) {
                var a = o[2], s = o[3];
                r[o[1]] = a.add, s && a.add(function() {
                    n = s
                }, t[1^e][2].disable, t[2][2].lock), i[o[0]] = function() {
                    return i[o[0] + "With"](this === i ? r : this, arguments), this
                }, i[o[0] + "With"] = a.fireWith
            }), r.promise(i), e && e.call(i, i), i
        },
        when: function(e) {
            var t, n, r, i = 0, o = Y.call(arguments), a = o.length, s = 1 !== a || e && oe.isFunction(e.promise) ? a: 0, l = 1 === s ? e: oe.Deferred(), u = function(e, n, r) {
                return function(i) {
                    n[e] = this, r[e] = arguments.length > 1 ? Y.call(arguments) : i, r === t ? l.notifyWith(n, r) : --s || l.resolveWith(n, r)
                }
            };
            if (a > 1)
                for (t = new Array(a), n = new Array(a), r = new Array(a); a > i; i++)
                    o[i] && oe.isFunction(o[i].promise) ? o[i].promise().done(u(i, r, o)).fail(l.reject).progress(u(i, n, t)) : --s;
            return s || l.resolveWith(r, o), l.promise()
        }
    });
    var Te;
    oe.fn.ready = function(e) {
        return oe.ready.promise().done(e), this
    }, oe.extend({
        isReady: !1,
        readyWait: 1,
        holdReady: function(e) {
            e ? oe.readyWait++ : oe.ready(!0)
        },
        ready: function(e) {
            if (e===!0?!--oe.readyWait : !oe.isReady) {
                if (!me.body)
                    return setTimeout(oe.ready);
                oe.isReady=!0, e!==!0&&--oe.readyWait > 0 || (Te.resolveWith(me, [oe]), oe.fn.trigger && oe(me).trigger("ready").off("ready"))
            }
        }
    }), oe.ready.promise = function(t) {
        if (!Te)
            if (Te = oe.Deferred(), "complete" === me.readyState)
                setTimeout(oe.ready);
            else if (me.addEventListener)
                me.addEventListener("DOMContentLoaded", s, !1), e.addEventListener("load", s, !1);
            else {
                me.attachEvent("onreadystatechange", s), e.attachEvent("onload", s);
                var n=!1;
                try {
                    n = null == e.frameElement && me.documentElement
                } catch (r) {}
                n && n.doScroll&&!function i() {
                    if (!oe.isReady) {
                        try {
                            n.doScroll("left")
                        } catch (e) {
                            return setTimeout(i, 50)
                        }
                        a(), oe.ready()
                    }
                }()
            }
        return Te.promise(t)
    };
    var Ce, Ne = "undefined";
    for (Ce in oe(re))
        break;
    re.ownLast = "0" !== Ce, re.inlineBlockNeedsLayout=!1, oe(function() {
        var e, t, n = me.getElementsByTagName("body")[0];
        n && (e = me.createElement("div"), e.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px", t = me.createElement("div"), n.appendChild(e).appendChild(t), typeof t.style.zoom !== Ne && (t.style.cssText = "border:0;margin:0;width:1px;padding:1px;display:inline;zoom:1", (re.inlineBlockNeedsLayout = 3 === t.offsetWidth) && (n.style.zoom = 1)), n.removeChild(e), e = t = null)
    }), function() {
        var e = me.createElement("div");
        if (null == re.deleteExpando) {
            re.deleteExpando=!0;
            try {
                delete e.test
            } catch (t) {
                re.deleteExpando=!1
            }
        }
        e = null
    }(), oe.acceptData = function(e) {
        var t = oe.noData[(e.nodeName + " ").toLowerCase()], n =+ e.nodeType || 1;
        return 1 !== n && 9 !== n?!1 : !t || t!==!0 && e.getAttribute("classid") === t
    };
    var Ee = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/, ke = /([A-Z])/g;
    oe.extend({
        cache: {},
        noData: {
            "applet ": !0,
            "embed ": !0,
            "object ": "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        },
        hasData: function(e) {
            return e = e.nodeType ? oe.cache[e[oe.expando]] : e[oe.expando], !!e&&!u(e)
        },
        data: function(e, t, n) {
            return c(e, t, n)
        },
        removeData: function(e, t) {
            return d(e, t)
        },
        _data: function(e, t, n) {
            return c(e, t, n, !0)
        },
        _removeData: function(e, t) {
            return d(e, t, !0)
        }
    }), oe.fn.extend({
        data: function(e, t) {
            var n, r, i, o = this[0], a = o && o.attributes;
            if (void 0 === e) {
                if (this.length && (i = oe.data(o), 1 === o.nodeType&&!oe._data(o, "parsedAttrs"))) {
                    for (n = a.length; n--;)
                        r = a[n].name, 0 === r.indexOf("data-") && (r = oe.camelCase(r.slice(5)), l(o, r, i[r]));
                    oe._data(o, "parsedAttrs", !0)
                }
                return i
            }
            return "object" == typeof e ? this.each(function() {
                oe.data(this, e)
            }) : arguments.length > 1 ? this.each(function() {
                oe.data(this, e, t)
            }) : o ? l(o, e, oe.data(o, e)) : void 0
        },
        removeData: function(e) {
            return this.each(function() {
                oe.removeData(this, e)
            })
        }
    }), oe.extend({
        queue: function(e, t, n) {
            var r;
            return e ? (t = (t || "fx") + "queue", r = oe._data(e, t), n && (!r || oe.isArray(n) ? r = oe._data(e, t, oe.makeArray(n)) : r.push(n)), r || []) : void 0
        },
        dequeue: function(e, t) {
            t = t || "fx";
            var n = oe.queue(e, t), r = n.length, i = n.shift(), o = oe._queueHooks(e, t), a = function() {
                oe.dequeue(e, t)
            };
            "inprogress" === i && (i = n.shift(), r--), i && ("fx" === t && n.unshift("inprogress"), delete o.stop, i.call(e, a, o)), !r && o && o.empty.fire()
        },
        _queueHooks: function(e, t) {
            var n = t + "queueHooks";
            return oe._data(e, n) || oe._data(e, n, {
                empty: oe.Callbacks("once memory").add(function() {
                    oe._removeData(e, t + "queue"), oe._removeData(e, n)
                })
            })
        }
    }), oe.fn.extend({
        queue: function(e, t) {
            var n = 2;
            return "string" != typeof e && (t = e, e = "fx", n--), arguments.length < n ? oe.queue(this[0], e) : void 0 === t ? this : this.each(function() {
                var n = oe.queue(this, e, t);
                oe._queueHooks(this, e), "fx" === e && "inprogress" !== n[0] && oe.dequeue(this, e)
            })
        },
        dequeue: function(e) {
            return this.each(function() {
                oe.dequeue(this, e)
            })
        },
        clearQueue: function(e) {
            return this.queue(e || "fx", [])
        },
        promise: function(e, t) {
            var n, r = 1, i = oe.Deferred(), o = this, a = this.length, s = function() {
                --r || i.resolveWith(o, [o])
            };
            for ("string" != typeof e && (t = e, e = void 0), e = e || "fx"; a--;)
                n = oe._data(o[a], e + "queueHooks"), n && n.empty && (r++, n.empty.add(s));
            return s(), i.promise(t)
        }
    });
    var Se = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, Ae = ["Top", "Right", "Bottom", "Left"], De = function(e, t) {
        return e = t || e, "none" === oe.css(e, "display") ||!oe.contains(e.ownerDocument, e)
    }, je = oe.access = function(e, t, n, r, i, o, a) {
        var s = 0, l = e.length, u = null == n;
        if ("object" === oe.type(n)) {
            i=!0;
            for (s in n)
                oe.access(e, t, s, n[s], !0, o, a)
        } else if (void 0 !== r && (i=!0, oe.isFunction(r) || (a=!0), u && (a ? (t.call(e, r), t = null) : (u = t, t = function(e, t, n) {
            return u.call(oe(e), n)
        })), t))for (; l > s; s++)
            t(e[s], n, a ? r : r.call(e[s], s, t(e[s], n)));
        return i ? e : u ? t.call(e) : l ? t(e[0], n) : o
    }, Le = /^(?:checkbox|radio)$/i;
    !function() {
        var e = me.createDocumentFragment(), t = me.createElement("div"), n = me.createElement("input");
        if (t.setAttribute("className", "t"), t.innerHTML = "  <link/><table></table><a href='/a'>a</a>", re.leadingWhitespace = 3 === t.firstChild.nodeType, re.tbody=!t.getElementsByTagName("tbody").length, re.htmlSerialize=!!t.getElementsByTagName("link").length, re.html5Clone = "<:nav></:nav>" !== me.createElement("nav").cloneNode(!0).outerHTML, n.type = "checkbox", n.checked=!0, e.appendChild(n), re.appendChecked = n.checked, t.innerHTML = "<textarea>x</textarea>", re.noCloneChecked=!!t.cloneNode(!0).lastChild.defaultValue, e.appendChild(t), t.innerHTML = "<input type='radio' checked='checked' name='t'/>", re.checkClone = t.cloneNode(!0).cloneNode(!0).lastChild.checked, re.noCloneEvent=!0, t.attachEvent && (t.attachEvent("onclick", function() {
            re.noCloneEvent=!1
        }), t.cloneNode(!0).click()), null == re.deleteExpando) {
            re.deleteExpando=!0;
            try {
                delete t.test
            } catch (r) {
                re.deleteExpando=!1
            }
        }
        e = t = n = null
    }(), function() {
        var t, n, r = me.createElement("div");
        for (t in{
            submit: !0,
            change: !0,
            focusin: !0
        })
            n = "on" + t, (re[t + "Bubbles"] = n in e) || (r.setAttribute(n, "t"), re[t + "Bubbles"] = r.attributes[n].expando===!1);
        r = null
    }();
    var He = /^(?:input|select|textarea)$/i, qe = /^key/, _e = /^(?:mouse|contextmenu)|click/, Me = /^(?:focusinfocus|focusoutblur)$/, Fe = /^([^.]*)(?:\.(.+)|)$/;
    oe.event = {
        global: {},
        add: function(e, t, n, r, i) {
            var o, a, s, l, u, c, d, f, p, h, m, g = oe._data(e);
            if (g) {
                for (n.handler && (l = n, n = l.handler, i = l.selector), n.guid || (n.guid = oe.guid++), (a = g.events) || (a = g.events = {}), (c = g.handle) || (c = g.handle = function(e) {
                    return typeof oe === Ne || e && oe.event.triggered === e.type ? void 0 : oe.event.dispatch.apply(c.elem, arguments)
                }, c.elem = e), t = (t || "").match(xe) || [""], s = t.length; s--;)
                    o = Fe.exec(t[s]) || [], p = m = o[1], h = (o[2] || "").split(".").sort(), p && (u = oe.event.special[p] || {}, p = (i ? u.delegateType : u.bindType) || p, u = oe.event.special[p] || {}, d = oe.extend({
                        type: p,
                        origType: m,
                        data: r,
                        handler: n,
                        guid: n.guid,
                        selector: i,
                        needsContext: i && oe.expr.match.needsContext.test(i),
                        namespace: h.join(".")
                    }, l), (f = a[p]) || (f = a[p] = [], f.delegateCount = 0, u.setup && u.setup.call(e, r, h, c)!==!1 || (e.addEventListener ? e.addEventListener(p, c, !1) : e.attachEvent && e.attachEvent("on" + p, c))), u.add && (u.add.call(e, d), d.handler.guid || (d.handler.guid = n.guid)), i ? f.splice(f.delegateCount++, 0, d) : f.push(d), oe.event.global[p]=!0);
                e = null
            }
        },
        remove: function(e, t, n, r, i) {
            var o, a, s, l, u, c, d, f, p, h, m, g = oe.hasData(e) && oe._data(e);
            if (g && (c = g.events)) {
                for (t = (t || "").match(xe) || [""], u = t.length; u--;)
                    if (s = Fe.exec(t[u]) || [], p = m = s[1], h = (s[2] || "").split(".").sort(), p) {
                        for (d = oe.event.special[p] || {}, p = (r ? d.delegateType : d.bindType) || p, f = c[p] || [], s = s[2] && new RegExp("(^|\\.)" + h.join("\\.(?:.*\\.|)") + "(\\.|$)"), l = o = f.length; o--;)
                            a = f[o], !i && m !== a.origType || n && n.guid !== a.guid || s&&!s.test(a.namespace) || r && r !== a.selector && ("**" !== r ||!a.selector) || (f.splice(o, 1), a.selector && f.delegateCount--, d.remove && d.remove.call(e, a));
                            l&&!f.length && (d.teardown && d.teardown.call(e, h, g.handle)!==!1 || oe.removeEvent(e, p, g.handle), delete c[p])
                    } else 
                        for (p in c)
                            oe.event.remove(e, p + t[u], n, r, !0);
                oe.isEmptyObject(c) && (delete g.handle, oe._removeData(e, "events"))
            }
        },
        trigger: function(t, n, r, i) {
            var o, a, s, l, u, c, d, f = [r || me], p = te.call(t, "type") ? t.type: t, h = te.call(t, "namespace") ? t.namespace.split("."): [];
            if (s = c = r = r || me, 3 !== r.nodeType && 8 !== r.nodeType&&!Me.test(p + oe.event.triggered) && (p.indexOf(".") >= 0 && (h = p.split("."), p = h.shift(), h.sort()), a = p.indexOf(":") < 0 && "on" + p, t = t[oe.expando] ? t : new oe.Event(p, "object" == typeof t && t), t.isTrigger = i ? 2 : 3, t.namespace = h.join("."), t.namespace_re = t.namespace ? new RegExp("(^|\\.)" + h.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, t.result = void 0, t.target || (t.target = r), n = null == n ? [t] : oe.makeArray(n, [t]), u = oe.event.special[p] || {}, i ||!u.trigger || u.trigger.apply(r, n)!==!1)
                ) {
                if (!i&&!u.noBubble&&!oe.isWindow(r)) {
                    for (l = u.delegateType || p, Me.test(l + p) || (s = s.parentNode); s; s = s.parentNode)
                        f.push(s), c = s;
                    c === (r.ownerDocument || me) && f.push(c.defaultView || c.parentWindow || e)
                }
                for (d = 0; (s = f[d++])&&!t.isPropagationStopped();)
                    t.type = d > 1 ? l : u.bindType || p, o = (oe._data(s, "events") || {})[t.type] && oe._data(s, "handle"), o && o.apply(s, n), o = a && s[a], o && o.apply && oe.acceptData(s) && (t.result = o.apply(s, n), t.result===!1 && t.preventDefault());
                if (t.type = p, !i&&!t.isDefaultPrevented() && (!u._default || u._default.apply(f.pop(), n)===!1) && oe.acceptData(r) && a && r[p]&&!oe.isWindow(r)) {
                    c = r[a], c && (r[a] = null), oe.event.triggered = p;
                    try {
                        r[p]()
                    } catch (m) {}
                    oe.event.triggered = void 0, c && (r[a] = c)
                }
                return t.result
            }
        },
        dispatch: function(e) {
            e = oe.event.fix(e);
            var t, n, r, i, o, a = [], s = Y.call(arguments), l = (oe._data(this, "events") || {})[e.type] || [], u = oe.event.special[e.type] || {};
            if (s[0] = e, e.delegateTarget = this, !u.preDispatch || u.preDispatch.call(this, e)!==!1) {
                for (a = oe.event.handlers.call(this, e, l), t = 0; (i = a[t++])&&!e.isPropagationStopped();)
                    for (e.currentTarget = i.elem, o = 0; (r = i.handlers[o++])&&!e.isImmediatePropagationStopped();)(!e.namespace_re || e.namespace_re.test(r.namespace)
                        ) && (e.handleObj = r, e.data = r.data, n = ((oe.event.special[r.origType] || {}).handle || r.handler).apply(i.elem, s), void 0 !== n && (e.result = n)===!1 && (e.preventDefault(), e.stopPropagation()));
                return u.postDispatch && u.postDispatch.call(this, e), e.result
            }
        },
        handlers: function(e, t) {
            var n, r, i, o, a = [], s = t.delegateCount, l = e.target;
            if (s && l.nodeType && (!e.button || "click" !== e.type))
                for (; l != this; l = l.parentNode || this)
                    if (1 === l.nodeType && (l.disabled!==!0 || "click" !== e.type)) {
                        for (i = [], o = 0; s > o; o++)
                            r = t[o], n = r.selector + " ", void 0 === i[n] && (i[n] = r.needsContext ? oe(n, this).index(l) >= 0 : oe.find(n, this, null, [l]).length), i[n] && i.push(r);
                            i.length && a.push({
                                elem: l,
                                handlers: i
                            })
                    }
            return s < t.length && a.push({
                elem: this,
                handlers: t.slice(s)
            }), a
        },
        fix: function(e) {
            if (e[oe.expando])
                return e;
            var t, n, r, i = e.type, o = e, a = this.fixHooks[i];
            for (a || (this.fixHooks[i] = a = _e.test(i) ? this.mouseHooks : qe.test(i) ? this.keyHooks : {}), r = a.props ? this.props.concat(a.props) : this.props, e = new oe.Event(o), t = r.length; t--;)
                n = r[t], e[n] = o[n];
            return e.target || (e.target = o.srcElement || me), 3 === e.target.nodeType && (e.target = e.target.parentNode), e.metaKey=!!e.metaKey, a.filter ? a.filter(e, o) : e
        },
        props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "),
            filter: function(e, t) {
                return null == e.which && (e.which = null != t.charCode ? t.charCode : t.keyCode), e
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function(e, t) {
                var n, r, i, o = t.button, a = t.fromElement;
                return null == e.pageX && null != t.clientX && (r = e.target.ownerDocument || me, i = r.documentElement, n = r.body, e.pageX = t.clientX + (i && i.scrollLeft || n && n.scrollLeft || 0) - (i && i.clientLeft || n && n.clientLeft || 0), e.pageY = t.clientY + (i && i.scrollTop || n && n.scrollTop || 0) - (i && i.clientTop || n && n.clientTop || 0)), !e.relatedTarget && a && (e.relatedTarget = a === e.target ? t.toElement : a), e.which || void 0 === o || (e.which = 1 & o ? 1 : 2 & o ? 3 : 4 & o ? 2 : 0), e
            }
        },
        special: {
            load: {
                noBubble: !0
            },
            focus: {
                trigger: function() {
                    if (this !== h() && this.focus)
                        try {
                            return this.focus(), !1
                    } catch (e) {}
                },
                delegateType: "focusin"
            },
            blur: {
                trigger: function() {
                    return this === h() && this.blur ? (this.blur(), !1) : void 0
                },
                delegateType: "focusout"
            },
            click: {
                trigger: function() {
                    return oe.nodeName(this, "input") && "checkbox" === this.type && this.click ? (this.click(), !1) : void 0
                },
                _default: function(e) {
                    return oe.nodeName(e.target, "a")
                }
            },
            beforeunload: {
                postDispatch: function(e) {
                    void 0 !== e.result && (e.originalEvent.returnValue = e.result)
                }
            }
        },
        simulate: function(e, t, n, r) {
            var i = oe.extend(new oe.Event, n, {
                type: e,
                isSimulated: !0,
                originalEvent: {}
            });
            r ? oe.event.trigger(i, null, t) : oe.event.dispatch.call(t, i), i.isDefaultPrevented() && n.preventDefault()
        }
    }, oe.removeEvent = me.removeEventListener ? function(e, t, n) {
        e.removeEventListener && e.removeEventListener(t, n, !1)
    } : function(e, t, n) {
        var r = "on" + t;
        e.detachEvent && (typeof e[r] === Ne && (e[r] = null), e.detachEvent(r, n))
    }, oe.Event = function(e, t) {
        return this instanceof oe.Event ? (e && e.type ? (this.originalEvent = e, this.type = e.type, this.isDefaultPrevented = e.defaultPrevented || void 0 === e.defaultPrevented && (e.returnValue===!1 || e.getPreventDefault && e.getPreventDefault()) ? f : p) : this.type = e, t && oe.extend(this, t), this.timeStamp = e && e.timeStamp || oe.now(), void(this[oe.expando]=!0)) : new oe.Event(e, t)
    }, oe.Event.prototype = {
        isDefaultPrevented: p,
        isPropagationStopped: p,
        isImmediatePropagationStopped: p,
        preventDefault: function() {
            var e = this.originalEvent;
            this.isDefaultPrevented = f, e && (e.preventDefault ? e.preventDefault() : e.returnValue=!1)
        },
        stopPropagation: function() {
            var e = this.originalEvent;
            this.isPropagationStopped = f, e && (e.stopPropagation && e.stopPropagation(), e.cancelBubble=!0)
        },
        stopImmediatePropagation: function() {
            this.isImmediatePropagationStopped = f, this.stopPropagation()
        }
    }, oe.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    }, function(e, t) {
        oe.event.special[e] = {
            delegateType: t,
            bindType: t,
            handle: function(e) {
                var n, r = this, i = e.relatedTarget, o = e.handleObj;
                return (!i || i !== r&&!oe.contains(r, i)) && (e.type = o.origType, n = o.handler.apply(this, arguments), e.type = t), n
            }
        }
    }), re.submitBubbles || (oe.event.special.submit = {
        setup: function() {
            return oe.nodeName(this, "form")?!1 : void oe.event.add(this, "click._submit keypress._submit", function(e) {
                var t = e.target, n = oe.nodeName(t, "input") || oe.nodeName(t, "button") ? t.form: void 0;
                n&&!oe._data(n, "submitBubbles") && (oe.event.add(n, "submit._submit", function(e) {
                    e._submit_bubble=!0
                }), oe._data(n, "submitBubbles", !0))
            })
        },
        postDispatch: function(e) {
            e._submit_bubble && (delete e._submit_bubble, this.parentNode&&!e.isTrigger && oe.event.simulate("submit", this.parentNode, e, !0))
        },
        teardown: function() {
            return oe.nodeName(this, "form")?!1 : void oe.event.remove(this, "._submit")
        }
    }), re.changeBubbles || (oe.event.special.change = {
        setup: function() {
            return He.test(this.nodeName) ? (("checkbox" === this.type || "radio" === this.type) && (oe.event.add(this, "propertychange._change", function(e) {
                "checked" === e.originalEvent.propertyName && (this._just_changed=!0)
            }), oe.event.add(this, "click._change", function(e) {
                this._just_changed&&!e.isTrigger && (this._just_changed=!1), oe.event.simulate("change", this, e, !0)
            })), !1) : void oe.event.add(this, "beforeactivate._change", function(e) {
                var t = e.target;
                He.test(t.nodeName)&&!oe._data(t, "changeBubbles") && (oe.event.add(t, "change._change", function(e) {
                    !this.parentNode || e.isSimulated || e.isTrigger || oe.event.simulate("change", this.parentNode, e, !0)
                }), oe._data(t, "changeBubbles", !0))
            })
        },
        handle: function(e) {
            var t = e.target;
            return this !== t || e.isSimulated || e.isTrigger || "radio" !== t.type && "checkbox" !== t.type ? e.handleObj.handler.apply(this, arguments) : void 0
        },
        teardown: function() {
            return oe.event.remove(this, "._change"), !He.test(this.nodeName)
        }
    }), re.focusinBubbles || oe.each({
        focus: "focusin",
        blur: "focusout"
    }, function(e, t) {
        var n = function(e) {
            oe.event.simulate(t, e.target, oe.event.fix(e), !0)
        };
        oe.event.special[t] = {
            setup: function() {
                var r = this.ownerDocument || this, i = oe._data(r, t);
                i || r.addEventListener(e, n, !0), oe._data(r, t, (i || 0) + 1)
            },
            teardown: function() {
                var r = this.ownerDocument || this, i = oe._data(r, t) - 1;
                i ? oe._data(r, t, i) : (r.removeEventListener(e, n, !0), oe._removeData(r, t))
            }
        }
    }), oe.fn.extend({
        on: function(e, t, n, r, i) {
            var o, a;
            if ("object" == typeof e) {
                "string" != typeof t && (n = n || t, t = void 0);
                for (o in e)
                    this.on(o, t, n, e[o], i);
                return this
            }
            if (null == n && null == r ? (r = t, n = t = void 0) : null == r && ("string" == typeof t ? (r = n, n = void 0) : (r = n, n = t, t = void 0)), r===!1)
                r = p;
            else if (!r)
                return this;
            return 1 === i && (a = r, r = function(e) {
                return oe().off(e), a.apply(this, arguments)
            }, r.guid = a.guid || (a.guid = oe.guid++)), this.each(function() {
                oe.event.add(this, e, r, n, t)
            })
        },
        one: function(e, t, n, r) {
            return this.on(e, t, n, r, 1)
        },
        off: function(e, t, n) {
            var r, i;
            if (e && e.preventDefault && e.handleObj)
                return r = e.handleObj, oe(e.delegateTarget).off(r.namespace ? r.origType + "." + r.namespace : r.origType, r.selector, r.handler), this;
            if ("object" == typeof e) {
                for (i in e)
                    this.off(i, t, e[i]);
                return this
            }
            return (t===!1 || "function" == typeof t) && (n = t, t = void 0), n===!1 && (n = p), this.each(function() {
                oe.event.remove(this, e, n, t)
            })
        },
        trigger: function(e, t) {
            return this.each(function() {
                oe.event.trigger(e, t, this)
            })
        },
        triggerHandler: function(e, t) {
            var n = this[0];
            return n ? oe.event.trigger(e, t, n, !0) : void 0
        }
    });
    var Oe = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video", Be = / jQuery\d+="(?:null|\d+)"/g, Pe = new RegExp("<(?:" + Oe + ")[\\s/>]", "i"), Re = /^\s+/, We = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, $e = /<([\w:]+)/, ze = /<tbody/i, Ie = /<|&#?\w+;/, Xe = /<(?:script|style|link)/i, Ue = /checked\s*(?:[^=]|=\s*.checked.)/i, Ve = /^$|\/(?:java|ecma)script/i, Je = /^true\/(.*)/, Ye = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, Ge = {
        option: [1, "<select multiple='multiple'>", "</select>"],
        legend: [1, "<fieldset>", "</fieldset>"],
        area: [1, "<map>", "</map>"],
        param: [1, "<object>", "</object>"],
        thead: [1, "<table>", "</table>"],
        tr: [2, "<table><tbody>", "</tbody></table>"],
        col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
        td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
        _default: re.htmlSerialize ? [0, "", ""]: [1, "X<div>", "</div>"]
    }, Qe = m(me), Ke = Qe.appendChild(me.createElement("div"));
    Ge.optgroup = Ge.option, Ge.tbody = Ge.tfoot = Ge.colgroup = Ge.caption = Ge.thead, Ge.th = Ge.td, oe.extend({
        clone: function(e, t, n) {
            var r, i, o, a, s, l = oe.contains(e.ownerDocument, e);
            if (re.html5Clone || oe.isXMLDoc(e) ||!Pe.test("<" + e.nodeName + ">") ? o = e.cloneNode(!0) : (Ke.innerHTML = e.outerHTML, Ke.removeChild(o = Ke.firstChild)), !(re.noCloneEvent && re.noCloneChecked || 1 !== e.nodeType && 11 !== e.nodeType || oe.isXMLDoc(e)))
                for (r = g(o), s = g(e), a = 0; null != (i = s[a]); ++a)
                    r[a] && C(i, r[a]);
            if (t)
                if (n)
                    for (s = s || g(e), r = r || g(o), a = 0; null != (i = s[a]); a++)
                        T(i, r[a]);
                else 
                    T(e, o);
            return r = g(o, "script"), r.length > 0 && w(r, !l && g(e, "script")), r = s = i = null, o
        },
        buildFragment: function(e, t, n, r) {
            for (var i, o, a, s, l, u, c, d = e.length, f = m(t), p = [], h = 0; d > h; h++)
                if (o = e[h], o || 0 === o)
                    if ("object" === oe.type(o))
                        oe.merge(p, o.nodeType ? [o] : o);
                    else if (Ie.test(o)) {
                        for (s = s || f.appendChild(t.createElement("div")), l = ($e.exec(o) || ["", ""])[1].toLowerCase(), c = Ge[l] || Ge._default, s.innerHTML = c[1] + o.replace(We, "<$1></$2>") + c[2], i = c[0]; i--;)
                            s = s.lastChild;
                            if (!re.leadingWhitespace && Re.test(o) && p.push(t.createTextNode(Re.exec(o)[0])), !re.tbody)
                                for (o = "table" !== l || ze.test(o) ? "<table>" !== c[1] || ze.test(o) ? 0 : s : s.firstChild, i = o && o.childNodes.length; i--;)
                                    oe.nodeName(u = o.childNodes[i], "tbody")&&!u.childNodes.length && o.removeChild(u);
                                    for (oe.merge(p, s.childNodes), s.textContent = ""; s.firstChild;)
                                        s.removeChild(s.firstChild);
                                        s = f.lastChild
                    } else 
                        p.push(t.createTextNode(o));
            for (s && f.removeChild(s), re.appendChecked || oe.grep(g(p, "input"), v), h = 0; o = p[h++];)
                if ((!r||-1 === oe.inArray(o, r)) && (a = oe.contains(o.ownerDocument, o), s = g(f.appendChild(o), "script"), a && w(s), n))
                    for (i = 0; o = s[i++];)
                        Ve.test(o.type || "") && n.push(o);
            return s = null, f
        },
        cleanData: function(e, t) {
            for (var n, r, i, o, a = 0, s = oe.expando, l = oe.cache, u = re.deleteExpando, c = oe.event.special; null != (n = e[a]); a++)
                if ((t || oe.acceptData(n)) && (i = n[s], o = i && l[i])) {
                    if (o.events)
                        for (r in o.events)
                            c[r] ? oe.event.remove(n, r) : oe.removeEvent(n, r, o.handle);
                            l[i] && (delete l[i], u ? delete n[s] : typeof n.removeAttribute !== Ne ? n.removeAttribute(s) : n[s] = null, J.push(i))
                }
        }
    }), oe.fn.extend({
        text: function(e) {
            return je(this, function(e) {
                return void 0 === e ? oe.text(this) : this.empty().append((this[0] && this[0].ownerDocument || me).createTextNode(e))
            }, null, e, arguments.length)
        },
        append: function() {
            return this.domManip(arguments, function(e) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var t = y(this, e);
                    t.appendChild(e)
                }
            })
        },
        prepend: function() {
            return this.domManip(arguments, function(e) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var t = y(this, e);
                    t.insertBefore(e, t.firstChild)
                }
            })
        },
        before: function() {
            return this.domManip(arguments, function(e) {
                this.parentNode && this.parentNode.insertBefore(e, this)
            })
        },
        after: function() {
            return this.domManip(arguments, function(e) {
                this.parentNode && this.parentNode.insertBefore(e, this.nextSibling)
            })
        },
        remove: function(e, t) {
            for (var n, r = e ? oe.filter(e, this) : this, i = 0; null != (n = r[i]); i++)
                t || 1 !== n.nodeType || oe.cleanData(g(n)), n.parentNode && (t && oe.contains(n.ownerDocument, n) && w(g(n, "script")), n.parentNode.removeChild(n));
            return this
        },
        empty: function() {
            for (var e, t = 0; null != (e = this[t]); t++) {
                for (1 === e.nodeType && oe.cleanData(g(e, !1)); e.firstChild;)
                    e.removeChild(e.firstChild);
                e.options && oe.nodeName(e, "select") && (e.options.length = 0)
            }
            return this
        },
        clone: function(e, t) {
            return e = null == e?!1 : e, t = null == t ? e : t, this.map(function() {
                return oe.clone(this, e, t)
            })
        },
        html: function(e) {
            return je(this, function(e) {
                var t = this[0] || {}, n = 0, r = this.length;
                if (void 0 === e)
                    return 1 === t.nodeType ? t.innerHTML.replace(Be, "") : void 0;
                if (!("string" != typeof e || Xe.test(e) ||!re.htmlSerialize && Pe.test(e) ||!re.leadingWhitespace && Re.test(e) || Ge[($e.exec(e) || ["", ""])[1].toLowerCase()])) {
                    e = e.replace(We, "<$1></$2>");
                    try {
                        for (; r > n; n++)
                            t = this[n] || {}, 1 === t.nodeType && (oe.cleanData(g(t, !1)), t.innerHTML = e);
                        t = 0
                    } catch (i) {}
                }
                t && this.empty().append(e)
            }, null, e, arguments.length)
        },
        replaceWith: function() {
            var e = arguments[0];
            return this.domManip(arguments, function(t) {
                e = this.parentNode, oe.cleanData(g(this)), e && e.replaceChild(t, this)
            }), e && (e.length || e.nodeType) ? this : this.remove()
        },
        detach: function(e) {
            return this.remove(e, !0)
        },
        domManip: function(e, t) {
            e = G.apply([], e);
            var n, r, i, o, a, s, l = 0, u = this.length, c = this, d = u - 1, f = e[0], p = oe.isFunction(f);
            if (p || u > 1 && "string" == typeof f&&!re.checkClone && Ue.test(f))
                return this.each(function(n) {
                    var r = c.eq(n);
                    p && (e[0] = f.call(this, n, r.html())), r.domManip(e, t)
                });
            if (u && (s = oe.buildFragment(e, this[0].ownerDocument, !1, this), n = s.firstChild, 1 === s.childNodes.length && (s = n), n)) {
                for (o = oe.map(g(s, "script"), b), i = o.length; u > l; l++)
                    r = s, l !== d && (r = oe.clone(r, !0, !0), i && oe.merge(o, g(r, "script"))), t.call(this[l], r, l);
                if (i)
                    for (a = o[o.length - 1].ownerDocument, oe.map(o, x), l = 0; i > l; l++)
                        r = o[l], Ve.test(r.type || "")&&!oe._data(r, "globalEval") && oe.contains(a, r) && (r.src ? oe._evalUrl && oe._evalUrl(r.src) : oe.globalEval((r.text || r.textContent || r.innerHTML || "").replace(Ye, "")));
                s = n = null
            }
            return this
        }
    }), oe.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    }, function(e, t) {
        oe.fn[e] = function(e) {
            for (var n, r = 0, i = [], o = oe(e), a = o.length - 1; a >= r; r++)
                n = r === a ? this : this.clone(!0), oe(o[r])[t](n), Q.apply(i, n.get());
            return this.pushStack(i)
        }
    });
    var Ze, et = {};
    !function() {
        var e, t, n = me.createElement("div"), r = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;padding:0;margin:0;border:0";
        n.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", e = n.getElementsByTagName("a")[0], e.style.cssText = "float:left;opacity:.5", re.opacity = /^0.5/.test(e.style.opacity), re.cssFloat=!!e.style.cssFloat, n.style.backgroundClip = "content-box", n.cloneNode(!0).style.backgroundClip = "", re.clearCloneStyle = "content-box" === n.style.backgroundClip, e = n = null, re.shrinkWrapBlocks = function() {
            var e, n, i, o;
            if (null == t) {
                if (e = me.getElementsByTagName("body")[0], !e)
                    return;
                o = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px", n = me.createElement("div"), i = me.createElement("div"), e.appendChild(n).appendChild(i), t=!1, typeof i.style.zoom !== Ne && (i.style.cssText = r + ";width:1px;padding:1px;zoom:1", i.innerHTML = "<div></div>", i.firstChild.style.width = "5px", t = 3 !== i.offsetWidth), e.removeChild(n), e = n = i = null
            }
            return t
        }
    }();
    var tt, nt, rt = /^margin/, it = new RegExp("^(" + Se + ")(?!px)[a-z%]+$", "i"), ot = /^(top|right|bottom|left)$/;
    e.getComputedStyle ? (tt = function(e) {
        return e.ownerDocument.defaultView.getComputedStyle(e, null)
    }, nt = function(e, t, n) {
        var r, i, o, a, s = e.style;
        return n = n || tt(e), a = n ? n.getPropertyValue(t) || n[t] : void 0, n && ("" !== a || oe.contains(e.ownerDocument, e) || (a = oe.style(e, t)), it.test(a) && rt.test(t) && (r = s.width, i = s.minWidth, o = s.maxWidth, s.minWidth = s.maxWidth = s.width = a,
        a = n.width, s.width = r, s.minWidth = i, s.maxWidth = o)), void 0 === a ? a : a + ""
    }) : me.documentElement.currentStyle && (tt = function(e) {
        return e.currentStyle
    }, nt = function(e, t, n) {
        var r, i, o, a, s = e.style;
        return n = n || tt(e), a = n ? n[t] : void 0, null == a && s && s[t] && (a = s[t]), it.test(a)&&!ot.test(t) && (r = s.left, i = e.runtimeStyle, o = i && i.left, o && (i.left = e.currentStyle.left), s.left = "fontSize" === t ? "1em" : a, a = s.pixelLeft + "px", s.left = r, o && (i.left = o)), void 0 === a ? a : a + "" || "auto"
    }), !function() {
        function t() {
            var t, n, r = me.getElementsByTagName("body")[0];
            r && (t = me.createElement("div"), n = me.createElement("div"), t.style.cssText = u, r.appendChild(t).appendChild(n), n.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;position:absolute;display:block;padding:1px;border:1px;width:4px;margin-top:1%;top:1%", oe.swap(r, null != r.style.zoom ? {
                zoom: 1
            } : {}, function() {
                i = 4 === n.offsetWidth
            }), o=!0, a=!1, s=!0, e.getComputedStyle && (a = "1%" !== (e.getComputedStyle(n, null) || {}).top, o = "4px" === (e.getComputedStyle(n, null) || {
                width: "4px"
            }).width), r.removeChild(t), n = r = null)
        }
        var n, r, i, o, a, s, l = me.createElement("div"), u = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px", c = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;padding:0;margin:0;border:0";
        l.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", n = l.getElementsByTagName("a")[0], n.style.cssText = "float:left;opacity:.5", re.opacity = /^0.5/.test(n.style.opacity), re.cssFloat=!!n.style.cssFloat, l.style.backgroundClip = "content-box", l.cloneNode(!0).style.backgroundClip = "", re.clearCloneStyle = "content-box" === l.style.backgroundClip, n = l = null, oe.extend(re, {
            reliableHiddenOffsets: function() {
                if (null != r)
                    return r;
                var e, t, n, i = me.createElement("div"), o = me.getElementsByTagName("body")[0];
                return o ? (i.setAttribute("className", "t"), i.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", e = me.createElement("div"), e.style.cssText = u, o.appendChild(e).appendChild(i), i.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", t = i.getElementsByTagName("td"), t[0].style.cssText = "padding:0;margin:0;border:0;display:none", n = 0 === t[0].offsetHeight, t[0].style.display = "", t[1].style.display = "none", r = n && 0 === t[0].offsetHeight, o.removeChild(e), i = o = null, r) : void 0
            },
            boxSizing: function() {
                return null == i && t(), i
            },
            boxSizingReliable: function() {
                return null == o && t(), o
            },
            pixelPosition: function() {
                return null == a && t(), a
            },
            reliableMarginRight: function() {
                var t, n, r, i;
                if (null == s && e.getComputedStyle) {
                    if (t = me.getElementsByTagName("body")[0], !t)
                        return;
                    n = me.createElement("div"), r = me.createElement("div"), n.style.cssText = u, t.appendChild(n).appendChild(r), i = r.appendChild(me.createElement("div")), i.style.cssText = r.style.cssText = c, i.style.marginRight = i.style.width = "0", r.style.width = "1px", s=!parseFloat((e.getComputedStyle(i, null) || {}).marginRight), t.removeChild(n)
                }
                return s
            }
        })
    }(), oe.swap = function(e, t, n, r) {
        var i, o, a = {};
        for (o in t)
            a[o] = e.style[o], e.style[o] = t[o];
        i = n.apply(e, r || []);
        for (o in t)
            e.style[o] = a[o];
        return i
    };
    var at = /alpha\([^)]*\)/i, st = /opacity\s*=\s*([^)]*)/, lt = /^(none|table(?!-c[ea]).+)/, ut = new RegExp("^(" + Se + ")(.*)$", "i"), ct = new RegExp("^([+-])=(" + Se + ")", "i"), dt = {
        position: "absolute",
        visibility: "hidden",
        display: "block"
    }, ft = {
        letterSpacing: 0,
        fontWeight: 400
    }, pt = ["Webkit", "O", "Moz", "ms"];
    oe.extend({
        cssHooks: {
            opacity: {
                get: function(e, t) {
                    if (t) {
                        var n = nt(e, "opacity");
                        return "" === n ? "1" : n
                    }
                }
            }
        },
        cssNumber: {
            columnCount: !0,
            fillOpacity: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            order: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {
            "float": re.cssFloat ? "cssFloat": "styleFloat"
        },
        style: function(e, t, n, r) {
            if (e && 3 !== e.nodeType && 8 !== e.nodeType && e.style) {
                var i, o, a, s = oe.camelCase(t), l = e.style;
                if (t = oe.cssProps[s] || (oe.cssProps[s] = S(l, s)), a = oe.cssHooks[t] || oe.cssHooks[s], void 0 === n)
                    return a && "get"in a && void 0 !== (i = a.get(e, !1, r)) ? i : l[t];
                if (o = typeof n, "string" === o && (i = ct.exec(n)) && (n = (i[1] + 1) * i[2] + parseFloat(oe.css(e, t)), o = "number"), null != n && n === n && ("number" !== o || oe.cssNumber[s] || (n += "px"), re.clearCloneStyle || "" !== n || 0 !== t.indexOf("background") || (l[t] = "inherit"), !(a && "set"in a && void 0 === (n = a.set(e, n, r)))
                    ))try {
                    l[t] = "", l[t] = n
                } catch (u) {}
            }
        },
        css: function(e, t, n, r) {
            var i, o, a, s = oe.camelCase(t);
            return t = oe.cssProps[s] || (oe.cssProps[s] = S(e.style, s)), a = oe.cssHooks[t] || oe.cssHooks[s], a && "get"in a && (o = a.get(e, !0, n)), void 0 === o && (o = nt(e, t, r)), "normal" === o && t in ft && (o = ft[t]), "" === n || n ? (i = parseFloat(o), n===!0 || oe.isNumeric(i) ? i || 0 : o) : o
        }
    }), oe.each(["height", "width"], function(e, t) {
        oe.cssHooks[t] = {
            get: function(e, n, r) {
                return n ? 0 === e.offsetWidth && lt.test(oe.css(e, "display")) ? oe.swap(e, dt, function() {
                    return L(e, t, r)
                }) : L(e, t, r) : void 0
            },
            set: function(e, n, r) {
                var i = r && tt(e);
                return D(e, n, r ? j(e, t, r, re.boxSizing() && "border-box" === oe.css(e, "boxSizing", !1, i), i) : 0)
            }
        }
    }), re.opacity || (oe.cssHooks.opacity = {
        get: function(e, t) {
            return st.test((t && e.currentStyle ? e.currentStyle.filter : e.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "" : t ? "1" : ""
        },
        set: function(e, t) {
            var n = e.style, r = e.currentStyle, i = oe.isNumeric(t) ? "alpha(opacity=" + 100 * t + ")": "", o = r && r.filter || n.filter || "";
            n.zoom = 1, (t >= 1 || "" === t) && "" === oe.trim(o.replace(at, "")) && n.removeAttribute && (n.removeAttribute("filter"), "" === t || r&&!r.filter) || (n.filter = at.test(o) ? o.replace(at, i) : o + " " + i)
        }
    }), oe.cssHooks.marginRight = k(re.reliableMarginRight, function(e, t) {
        return t ? oe.swap(e, {
            display: "inline-block"
        }, nt, [e, "marginRight"]) : void 0
    }), oe.each({
        margin: "",
        padding: "",
        border: "Width"
    }, function(e, t) {
        oe.cssHooks[e + t] = {
            expand: function(n) {
                for (var r = 0, i = {}, o = "string" == typeof n ? n.split(" ") : [n]; 4 > r; r++)
                    i[e + Ae[r] + t] = o[r] || o[r - 2] || o[0];
                return i
            }
        }, rt.test(e) || (oe.cssHooks[e + t].set = D)
    }), oe.fn.extend({
        css: function(e, t) {
            return je(this, function(e, t, n) {
                var r, i, o = {}, a = 0;
                if (oe.isArray(t)) {
                    for (r = tt(e), i = t.length; i > a; a++)
                        o[t[a]] = oe.css(e, t[a], !1, r);
                    return o
                }
                return void 0 !== n ? oe.style(e, t, n) : oe.css(e, t)
            }, e, t, arguments.length > 1)
        },
        show: function() {
            return A(this, !0)
        },
        hide: function() {
            return A(this)
        },
        toggle: function(e) {
            return "boolean" == typeof e ? e ? this.show() : this.hide() : this.each(function() {
                De(this) ? oe(this).show() : oe(this).hide()
            })
        }
    }), oe.Tween = H, H.prototype = {
        constructor: H,
        init: function(e, t, n, r, i, o) {
            this.elem = e, this.prop = n, this.easing = i || "swing", this.options = t, this.start = this.now = this.cur(), this.end = r, this.unit = o || (oe.cssNumber[n] ? "" : "px")
        },
        cur: function() {
            var e = H.propHooks[this.prop];
            return e && e.get ? e.get(this) : H.propHooks._default.get(this)
        },
        run: function(e) {
            var t, n = H.propHooks[this.prop];
            return this.pos = t = this.options.duration ? oe.easing[this.easing](e, this.options.duration * e, 0, 1, this.options.duration) : e, this.now = (this.end - this.start) * t + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), n && n.set ? n.set(this) : H.propHooks._default.set(this), this
        }
    }, H.prototype.init.prototype = H.prototype, H.propHooks = {
        _default: {
            get: function(e) {
                var t;
                return null == e.elem[e.prop] || e.elem.style && null != e.elem.style[e.prop] ? (t = oe.css(e.elem, e.prop, ""), t && "auto" !== t ? t : 0) : e.elem[e.prop]
            },
            set: function(e) {
                oe.fx.step[e.prop] ? oe.fx.step[e.prop](e) : e.elem.style && (null != e.elem.style[oe.cssProps[e.prop]] || oe.cssHooks[e.prop]) ? oe.style(e.elem, e.prop, e.now + e.unit) : e.elem[e.prop] = e.now
            }
        }
    }, H.propHooks.scrollTop = H.propHooks.scrollLeft = {
        set: function(e) {
            e.elem.nodeType && e.elem.parentNode && (e.elem[e.prop] = e.now)
        }
    }, oe.easing = {
        linear: function(e) {
            return e
        },
        swing: function(e) {
            return .5 - Math.cos(e * Math.PI) / 2
        }
    }, oe.fx = H.prototype.init, oe.fx.step = {};
    var ht, mt, gt = /^(?:toggle|show|hide)$/, vt = new RegExp("^(?:([+-])=|)(" + Se + ")([a-z%]*)$", "i"), yt = /queueHooks$/, bt = [F], xt = {
        "*": [function(e, t) {
            var n = this.createTween(e, t), r = n.cur(), i = vt.exec(t), o = i && i[3] || (oe.cssNumber[e] ? "" : "px"), a = (oe.cssNumber[e] || "px" !== o&&+r) && vt.exec(oe.css(n.elem, e)), s = 1, l = 20;
            if (a && a[3] !== o) {
                o = o || a[3], i = i || [], a =+ r || 1;
                do 
                    s = s || ".5", a/=s, oe.style(n.elem, e, a + o);
                while (s !== (s = n.cur() / r) && 1 !== s&&--l)
                }
            return i && (a = n.start =+ a||+r || 0, n.unit = o, n.end = i[1] ? a + (i[1] + 1) * i[2] : + i[2]), n
        }
        ]
    };
    oe.Animation = oe.extend(B, {
        tweener: function(e, t) {
            oe.isFunction(e) ? (t = e, e = ["*"]): e = e.split(" ");
            for (var n,
            r = 0,
            i = e.length;
            i > r;
            r++)n = e[r],
            xt[n] = xt[n] || [],
            xt[n].unshift(t)
        }, prefilter : function(e, t) {
            t ? bt.unshift(e) : bt.push(e)
        }
    }), oe.speed = function(e, t, n) {
        var r = e && "object" == typeof e ? oe.extend({}, e): {
            complete: n ||!n && t || oe.isFunction(e) && e,
            duration: e,
            easing: n && t || t&&!oe.isFunction(t) && t
        };
        return r.duration = oe.fx.off ? 0 : "number" == typeof r.duration ? r.duration : r.duration in oe.fx.speeds ? oe.fx.speeds[r.duration] : oe.fx.speeds._default, (null == r.queue || r.queue===!0) && (r.queue = "fx"), r.old = r.complete, r.complete = function() {
            oe.isFunction(r.old) && r.old.call(this), r.queue && oe.dequeue(this, r.queue)
        }, r
    }, oe.fn.extend({
        fadeTo: function(e, t, n, r) {
            return this.filter(De).css("opacity", 0).show().end().animate({
                opacity: t
            }, e, n, r)
        },
        animate: function(e, t, n, r) {
            var i = oe.isEmptyObject(e), o = oe.speed(t, n, r), a = function() {
                var t = B(this, oe.extend({}, e), o);
                (i || oe._data(this, "finish")) && t.stop(!0)
            };
            return a.finish = a, i || o.queue===!1 ? this.each(a) : this.queue(o.queue, a)
        },
        stop: function(e, t, n) {
            var r = function(e) {
                var t = e.stop;
                delete e.stop, t(n)
            };
            return "string" != typeof e && (n = t, t = e, e = void 0), t && e!==!1 && this.queue(e || "fx", []), this.each(function() {
                var t=!0, i = null != e && e + "queueHooks", o = oe.timers, a = oe._data(this);
                if (i)
                    a[i] && a[i].stop && r(a[i]);
                else 
                    for (i in a)
                        a[i] && a[i].stop && yt.test(i) && r(a[i]);
                for (i = o.length; i--;)
                    o[i].elem !== this || null != e && o[i].queue !== e || (o[i].anim.stop(n), t=!1, o.splice(i, 1));
                (t ||!n) && oe.dequeue(this, e)
            })
        },
        finish: function(e) {
            return e!==!1 && (e = e || "fx"), this.each(function() {
                var t, n = oe._data(this), r = n[e + "queue"], i = n[e + "queueHooks"], o = oe.timers, a = r ? r.length: 0;
                for (n.finish=!0, oe.queue(this, e, []), i && i.stop && i.stop.call(this, !0), t = o.length; t--;)
                    o[t].elem === this && o[t].queue === e && (o[t].anim.stop(!0), o.splice(t, 1));
                for (t = 0; a > t; t++)
                    r[t] && r[t].finish && r[t].finish.call(this);
                delete n.finish
            })
        }
    }), oe.each(["toggle", "show", "hide"], function(e, t) {
        var n = oe.fn[t];
        oe.fn[t] = function(e, r, i) {
            return null == e || "boolean" == typeof e ? n.apply(this, arguments) : this.animate(_(t, !0), e, r, i)
        }
    }), oe.each({
        slideDown: _("show"),
        slideUp: _("hide"),
        slideToggle: _("toggle"),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        },
        fadeToggle: {
            opacity: "toggle"
        }
    }, function(e, t) {
        oe.fn[e] = function(e, n, r) {
            return this.animate(t, e, n, r)
        }
    }), oe.timers = [], oe.fx.tick = function() {
        var e, t = oe.timers, n = 0;
        for (ht = oe.now(); n < t.length; n++)
            e = t[n], e() || t[n] !== e || t.splice(n--, 1);
        t.length || oe.fx.stop(), ht = void 0
    }, oe.fx.timer = function(e) {
        oe.timers.push(e), e() ? oe.fx.start() : oe.timers.pop()
    }, oe.fx.interval = 13, oe.fx.start = function() {
        mt || (mt = setInterval(oe.fx.tick, oe.fx.interval))
    }, oe.fx.stop = function() {
        clearInterval(mt), mt = null
    }, oe.fx.speeds = {
        slow: 600,
        fast: 200,
        _default: 400
    }, oe.fn.delay = function(e, t) {
        return e = oe.fx ? oe.fx.speeds[e] || e : e, t = t || "fx", this.queue(t, function(t, n) {
            var r = setTimeout(t, e);
            n.stop = function() {
                clearTimeout(r)
            }
        })
    }, function() {
        var e, t, n, r, i = me.createElement("div");
        i.setAttribute("className", "t"), i.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", e = i.getElementsByTagName("a")[0], n = me.createElement("select"), r = n.appendChild(me.createElement("option")), t = i.getElementsByTagName("input")[0], e.style.cssText = "top:1px", re.getSetAttribute = "t" !== i.className, re.style = /top/.test(e.getAttribute("style")), re.hrefNormalized = "/a" === e.getAttribute("href"), re.checkOn=!!t.value, re.optSelected = r.selected, re.enctype=!!me.createElement("form").enctype, n.disabled=!0, re.optDisabled=!r.disabled, t = me.createElement("input"), t.setAttribute("value", ""), re.input = "" === t.getAttribute("value"), t.value = "t", t.setAttribute("type", "radio"), re.radioValue = "t" === t.value, e = t = n = r = i = null
    }();
    var wt = /\r/g;
    oe.fn.extend({
        val: function(e) {
            var t, n, r, i = this[0];
            return arguments.length ? (r = oe.isFunction(e), this.each(function(n) {
                var i;
                1 === this.nodeType && (i = r ? e.call(this, n, oe(this).val()) : e, null == i ? i = "" : "number" == typeof i ? i += "" : oe.isArray(i) && (i = oe.map(i, function(e) {
                    return null == e ? "" : e + ""
                })), t = oe.valHooks[this.type] || oe.valHooks[this.nodeName.toLowerCase()], t && "set"in t && void 0 !== t.set(this, i, "value") || (this.value = i))
            })) : i ? (t = oe.valHooks[i.type] || oe.valHooks[i.nodeName.toLowerCase()], t && "get"in t && void 0 !== (n = t.get(i, "value")) ? n : (n = i.value, "string" == typeof n ? n.replace(wt, "") : null == n ? "" : n)) : void 0
        }
    }), oe.extend({
        valHooks: {
            option: {
                get: function(e) {
                    var t = oe.find.attr(e, "value");
                    return null != t ? t : oe.text(e)
                }
            },
            select: {
                get: function(e) {
                    for (var t, n, r = e.options, i = e.selectedIndex, o = "select-one" === e.type || 0 > i, a = o ? null : [], s = o ? i + 1 : r.length, l = 0 > i ? s : o ? i : 0; s > l; l++)
                        if (n = r[l], !(!n.selected && l !== i || (re.optDisabled ? n.disabled : null !== n.getAttribute("disabled")) || n.parentNode.disabled && oe.nodeName(n.parentNode, "optgroup"))) {
                            if (t = oe(n).val(), o)
                                return t;
                                a.push(t)
                        }
                    return a
                },
                set: function(e, t) {
                    for (var n, r, i = e.options, o = oe.makeArray(t), a = i.length; a--;)
                        if (r = i[a], oe.inArray(oe.valHooks.option.get(r), o) >= 0)
                            try {
                                r.selected = n=!0
                    } catch (s) {
                        r.scrollHeight
                    } else 
                        r.selected=!1;
                    return n || (e.selectedIndex =- 1), i
                }
            }
        }
    }), oe.each(["radio", "checkbox"], function() {
        oe.valHooks[this] = {
            set: function(e, t) {
                return oe.isArray(t) ? e.checked = oe.inArray(oe(e).val(), t) >= 0 : void 0
            }
        }, re.checkOn || (oe.valHooks[this].get = function(e) {
            return null === e.getAttribute("value") ? "on" : e.value
        })
    });
    var Tt, Ct, Nt = oe.expr.attrHandle, Et = /^(?:checked|selected)$/i, kt = re.getSetAttribute, St = re.input;
    oe.fn.extend({
        attr: function(e, t) {
            return je(this, oe.attr, e, t, arguments.length > 1)
        },
        removeAttr: function(e) {
            return this.each(function() {
                oe.removeAttr(this, e)
            })
        }
    }), oe.extend({
        attr: function(e, t, n) {
            var r, i, o = e.nodeType;
            return e && 3 !== o && 8 !== o && 2 !== o ? typeof e.getAttribute === Ne ? oe.prop(e, t, n) : (1 === o && oe.isXMLDoc(e) || (t = t.toLowerCase(), r = oe.attrHooks[t] || (oe.expr.match.bool.test(t) ? Ct : Tt)), void 0 === n ? r && "get"in r && null !== (i = r.get(e, t)) ? i : (i = oe.find.attr(e, t), null == i ? void 0 : i) : null !== n ? r && "set"in r && void 0 !== (i = r.set(e, n, t)) ? i : (e.setAttribute(t, n + ""), n) : void oe.removeAttr(e, t)) : void 0
        },
        removeAttr: function(e, t) {
            var n, r, i = 0, o = t && t.match(xe);
            if (o && 1 === e.nodeType)
                for (; n = o[i++];)
                    r = oe.propFix[n] || n, oe.expr.match.bool.test(n) ? St && kt ||!Et.test(n) ? e[r]=!1 : e[oe.camelCase("default-" + n)] = e[r]=!1 : oe.attr(e, n, ""), e.removeAttribute(kt ? n : r)
        },
        attrHooks: {
            type: {
                set: function(e, t) {
                    if (!re.radioValue && "radio" === t && oe.nodeName(e, "input")) {
                        var n = e.value;
                        return e.setAttribute("type", t), n && (e.value = n), t
                    }
                }
            }
        }
    }), Ct = {
        set: function(e, t, n) {
            return t===!1 ? oe.removeAttr(e, n) : St && kt ||!Et.test(n) ? e.setAttribute(!kt && oe.propFix[n] || n, n) : e[oe.camelCase("default-" + n)] = e[n]=!0, n
        }
    }, oe.each(oe.expr.match.bool.source.match(/\w+/g), function(e, t) {
        var n = Nt[t] || oe.find.attr;
        Nt[t] = St && kt ||!Et.test(t) ? function(e, t, r) {
            var i, o;
            return r || (o = Nt[t], Nt[t] = i, i = null != n(e, t, r) ? t.toLowerCase() : null, Nt[t] = o), i
        } : function(e, t, n) {
            return n ? void 0 : e[oe.camelCase("default-" + t)] ? t.toLowerCase() : null
        }
    }), St && kt || (oe.attrHooks.value = {
        set: function(e, t, n) {
            return oe.nodeName(e, "input") ? void(e.defaultValue = t) : Tt && Tt.set(e, t, n)
        }
    }), kt || (Tt = {
        set: function(e, t, n) {
            var r = e.getAttributeNode(n);
            return r || e.setAttributeNode(r = e.ownerDocument.createAttribute(n)), r.value = t += "", "value" === n || t === e.getAttribute(n) ? t : void 0
        }
    }, Nt.id = Nt.name = Nt.coords = function(e, t, n) {
        var r;
        return n ? void 0 : (r = e.getAttributeNode(t)) && "" !== r.value ? r.value : null
    }, oe.valHooks.button = {
        get: function(e, t) {
            var n = e.getAttributeNode(t);
            return n && n.specified ? n.value : void 0
        },
        set: Tt.set
    }, oe.attrHooks.contenteditable = {
        set: function(e, t, n) {
            Tt.set(e, "" === t?!1 : t, n)
        }
    }, oe.each(["width", "height"], function(e, t) {
        oe.attrHooks[t] = {
            set: function(e, n) {
                return "" === n ? (e.setAttribute(t, "auto"), n) : void 0
            }
        }
    })), re.style || (oe.attrHooks.style = {
        get: function(e) {
            return e.style.cssText || void 0
        },
        set: function(e, t) {
            return e.style.cssText = t + ""
        }
    });
    var At = /^(?:input|select|textarea|button|object)$/i, Dt = /^(?:a|area)$/i;
    oe.fn.extend({
        prop: function(e, t) {
            return je(this, oe.prop, e, t, arguments.length > 1)
        },
        removeProp: function(e) {
            return e = oe.propFix[e] || e, this.each(function() {
                try {
                    this[e] = void 0, delete this[e]
                } catch (t) {}
            })
        }
    }), oe.extend({
        propFix: {
            "for": "htmlFor",
            "class": "className"
        },
        prop: function(e, t, n) {
            var r, i, o, a = e.nodeType;
            return e && 3 !== a && 8 !== a && 2 !== a ? (o = 1 !== a ||!oe.isXMLDoc(e), o && (t = oe.propFix[t] || t, i = oe.propHooks[t]), void 0 !== n ? i && "set"in i && void 0 !== (r = i.set(e, n, t)) ? r : e[t] = n : i && "get"in i && null !== (r = i.get(e, t)) ? r : e[t]) : void 0
        },
        propHooks: {
            tabIndex: {
                get: function(e) {
                    var t = oe.find.attr(e, "tabindex");
                    return t ? parseInt(t, 10) : At.test(e.nodeName) || Dt.test(e.nodeName) && e.href ? 0 : - 1
                }
            }
        }
    }), re.hrefNormalized || oe.each(["href", "src"], function(e, t) {
        oe.propHooks[t] = {
            get: function(e) {
                return e.getAttribute(t, 4)
            }
        }
    }), re.optSelected || (oe.propHooks.selected = {
        get: function(e) {
            var t = e.parentNode;
            return t && (t.selectedIndex, t.parentNode && t.parentNode.selectedIndex), null
        }
    }), oe.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"], function() {
        oe.propFix[this.toLowerCase()] = this
    }), re.enctype || (oe.propFix.enctype = "encoding");
    var jt = /[\t\r\n\f]/g;
    oe.fn.extend({
        addClass: function(e) {
            var t, n, r, i, o, a, s = 0, l = this.length, u = "string" == typeof e && e;
            if (oe.isFunction(e))
                return this.each(function(t) {
                    oe(this).addClass(e.call(this, t, this.className))
                });
            if (u)
                for (t = (e || "").match(xe) || []; l > s; s++)
                    if (n = this[s], r = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(jt, " ") : " ")) {
                        for (o = 0; i = t[o++];)
                            r.indexOf(" " + i + " ") < 0 && (r += i + " ");
                            a = oe.trim(r), n.className !== a && (n.className = a)
                    }
            return this
        },
        removeClass: function(e) {
            var t, n, r, i, o, a, s = 0, l = this.length, u = 0 === arguments.length || "string" == typeof e && e;
            if (oe.isFunction(e))
                return this.each(function(t) {
                    oe(this).removeClass(e.call(this, t, this.className))
                });
            if (u)
                for (t = (e || "").match(xe) || []; l > s; s++)
                    if (n = this[s], r = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(jt, " ") : "")) {
                        for (o = 0; i = t[o++];)
                            for (; r.indexOf(" " + i + " ") >= 0;)
                                r = r.replace(" " + i + " ", " ");
                                a = e ? oe.trim(r) : "", n.className !== a && (n.className = a)
                    }
            return this
        },
        toggleClass: function(e, t) {
            var n = typeof e;
            return "boolean" == typeof t && "string" === n ? t ? this.addClass(e) : this.removeClass(e) : this.each(oe.isFunction(e) ? function(n) {
                oe(this).toggleClass(e.call(this, n, this.className, t), t)
            } : function() {
                if ("string" === n)
                    for (var t, r = 0, i = oe(this), o = e.match(xe) || []; t = o[r++];)
                        i.hasClass(t) ? i.removeClass(t) : i.addClass(t);
                else (n === Ne || "boolean" === n) 
                    && (this.className && oe._data(this, "__className__", this.className), this.className = this.className || e===!1 ? "" : oe._data(this, "__className__") || "")
            })
        },
        hasClass: function(e) {
            for (var t = " " + e + " ", n = 0, r = this.length; r > n; n++)
                if (1 === this[n].nodeType && (" " + this[n].className + " ").replace(jt, " ").indexOf(t) >= 0)
                    return !0;
            return !1
        }
    }), oe.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function(e, t) {
        oe.fn[t] = function(e, n) {
            return arguments.length > 0 ? this.on(t, null, e, n) : this.trigger(t)
        }
    }), oe.fn.extend({
        hover: function(e, t) {
            return this.mouseenter(e).mouseleave(t || e)
        },
        bind: function(e, t, n) {
            return this.on(e, null, t, n)
        },
        unbind: function(e, t) {
            return this.off(e, null, t)
        },
        delegate: function(e, t, n, r) {
            return this.on(t, e, n, r)
        },
        undelegate: function(e, t, n) {
            return 1 === arguments.length ? this.off(e, "**") : this.off(t, e || "**", n)
        }
    });
    var Lt = oe.now(), Ht = /\?/, qt = /(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g;
    oe.parseJSON = function(t) {
        if (e.JSON && e.JSON.parse)
            return e.JSON.parse(t + "");
        var n, r = null, i = oe.trim(t + "");
        return i&&!oe.trim(i.replace(qt, function(e, t, i, o) {
            return n && t && (r = 0), 0 === r ? e : (n = i || t, r+=!o-!i, "")
        })) ? Function("return " + i)() : oe.error("Invalid JSON: " + t)
    }, oe.parseXML = function(t) {
        var n, r;
        if (!t || "string" != typeof t)
            return null;
        try {
            e.DOMParser ? (r = new DOMParser, n = r.parseFromString(t, "text/xml")) : (n = new ActiveXObject("Microsoft.XMLDOM"), n.async = "false", n.loadXML(t))
        } catch (i) {
            n = void 0
        }
        return n && n.documentElement&&!n.getElementsByTagName("parsererror").length || oe.error("Invalid XML: " + t), n
    };
    var _t, Mt, Ft = /#.*$/, Ot = /([?&])_=[^&]*/, Bt = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm, Pt = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/, Rt = /^(?:GET|HEAD)$/, Wt = /^\/\//, $t = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/, zt = {}, It = {}, Xt = "*/".concat("*");
    try {
        Mt = location.href
    } catch (Ut) {
        Mt = me.createElement("a"), Mt.href = "", Mt = Mt.href
    }
    _t = $t.exec(Mt.toLowerCase()) || [], oe.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: Mt,
            type: "GET",
            isLocal: Pt.test(_t[1]),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": Xt,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText",
                json: "responseJSON"
            },
            converters: {
                "* text": String,
                "text html": !0,
                "text json": oe.parseJSON,
                "text xml": oe.parseXML
            },
            flatOptions: {
                url: !0,
                context: !0
            }
        },
        ajaxSetup: function(e, t) {
            return t ? W(W(e, oe.ajaxSettings), t) : W(oe.ajaxSettings, e)
        },
        ajaxPrefilter: P(zt),
        ajaxTransport: P(It),
        ajax: function(e, t) {
            function n(e, t, n, r) {
                var i, c, v, y, x, T = t;
                2 !== b && (b = 2, s && clearTimeout(s), u = void 0, a = r || "", w.readyState = e > 0 ? 4 : 0, i = e >= 200 && 300 > e || 304 === e, n && (y = $(d, w, n)), y = z(d, y, w, i), i ? (d.ifModified && (x = w.getResponseHeader("Last-Modified"), x && (oe.lastModified[o] = x), x = w.getResponseHeader("etag"), x && (oe.etag[o] = x)), 204 === e || "HEAD" === d.type ? T = "nocontent" : 304 === e ? T = "notmodified" : (T = y.state, c = y.data, v = y.error, i=!v)) : (v = T, (e ||!T) && (T = "error", 0 > e && (e = 0))), w.status = e, w.statusText = (t || T) + "", i ? h.resolveWith(f, [c, T, w]) : h.rejectWith(f, [w, T, v]), w.statusCode(g), g = void 0, l && p.trigger(i ? "ajaxSuccess" : "ajaxError", [w, d, i ? c: v]), m.fireWith(f, [w, T]), l && (p.trigger("ajaxComplete", [w, d]), --oe.active || oe.event.trigger("ajaxStop")))
            }
            "object" == typeof e && (t = e, e = void 0), t = t || {};
            var r, i, o, a, s, l, u, c, d = oe.ajaxSetup({}, t), f = d.context || d, p = d.context && (f.nodeType || f.jquery) ? oe(f): oe.event, h = oe.Deferred(), m = oe.Callbacks("once memory"), g = d.statusCode || {}, v = {}, y = {}, b = 0, x = "canceled", w = {
                readyState: 0,
                getResponseHeader: function(e) {
                    var t;
                    if (2 === b) {
                        if (!c)
                            for (c = {}; t = Bt.exec(a);)
                                c[t[1].toLowerCase()] = t[2];
                        t = c[e.toLowerCase()]
                    }
                    return null == t ? null : t
                },
                getAllResponseHeaders: function() {
                    return 2 === b ? a : null
                },
                setRequestHeader: function(e, t) {
                    var n = e.toLowerCase();
                    return b || (e = y[n] = y[n] || e, v[e] = t), this
                },
                overrideMimeType: function(e) {
                    return b || (d.mimeType = e), this
                },
                statusCode: function(e) {
                    var t;
                    if (e)
                        if (2 > b)
                            for (t in e)
                                g[t] = [g[t], e[t]];
                        else 
                            w.always(e[w.status]);
                    return this
                },
                abort: function(e) {
                    var t = e || x;
                    return u && u.abort(t), n(0, t), this
                }
            };
            if (h.promise(w).complete = m.add, w.success = w.done, w.error = w.fail, d.url = ((e || d.url || Mt) + "").replace(Ft, "").replace(Wt, _t[1] + "//"), d.type = t.method || t.type || d.method || d.type, d.dataTypes = oe.trim(d.dataType || "*").toLowerCase().match(xe) || [""], null == d.crossDomain && (r = $t.exec(d.url.toLowerCase()), d.crossDomain=!(!r || r[1] === _t[1] && r[2] === _t[2] && (r[3] || ("http:" === r[1] ? "80" : "443")) === (_t[3] || ("http:" === _t[1] ? "80" : "443")))), d.data && d.processData && "string" != typeof d.data && (d.data = oe.param(d.data, d.traditional)), R(zt, d, t, w), 2 === b)
                return w;
            l = d.global, l && 0 === oe.active++&&oe.event.trigger("ajaxStart"), d.type = d.type.toUpperCase(), d.hasContent=!Rt.test(d.type), o = d.url, d.hasContent || (d.data && (o = d.url += (Ht.test(o) ? "&" : "?") + d.data, delete d.data), d.cache===!1 && (d.url = Ot.test(o) ? o.replace(Ot, "$1_=" + Lt++) : o + (Ht.test(o) ? "&" : "?") + "_=" + Lt++)), d.ifModified && (oe.lastModified[o] && w.setRequestHeader("If-Modified-Since", oe.lastModified[o]), oe.etag[o] && w.setRequestHeader("If-None-Match", oe.etag[o])), (d.data && d.hasContent && d.contentType!==!1 || t.contentType) && w.setRequestHeader("Content-Type", d.contentType), w.setRequestHeader("Accept", d.dataTypes[0] && d.accepts[d.dataTypes[0]] ? d.accepts[d.dataTypes[0]] + ("*" !== d.dataTypes[0] ? ", " + Xt + "; q=0.01" : "") : d.accepts["*"]);
            for (i in d.headers)
                w.setRequestHeader(i, d.headers[i]);
            if (d.beforeSend && (d.beforeSend.call(f, w, d)===!1 || 2 === b))
                return w.abort();
            x = "abort";
            for (i in{
                success: 1,
                error: 1,
                complete: 1
            })
                w[i](d[i]);
            if (u = R(It, d, t, w)) {
                w.readyState = 1, l && p.trigger("ajaxSend", [w, d]), d.async && d.timeout > 0 && (s = setTimeout(function() {
                    w.abort("timeout")
                }, d.timeout));
                try {
                    b = 1, u.send(v, n)
                } catch (T) {
                    if (!(2 > b))
                        throw T;
                    n( - 1, T)
                }
            } else 
                n( - 1, "No Transport");
            return w
        },
        getJSON: function(e, t, n) {
            return oe.get(e, t, n, "json")
        },
        getScript: function(e, t) {
            return oe.get(e, void 0, t, "script")
        }
    }), oe.each(["get", "post"], function(e, t) {
        oe[t] = function(e, n, r, i) {
            return oe.isFunction(n) && (i = i || r, r = n, n = void 0), oe.ajax({
                url: e,
                type: t,
                dataType: i,
                data: n,
                success: r
            })
        }
    }), oe.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function(e, t) {
        oe.fn[t] = function(e) {
            return this.on(t, e)
        }
    }), oe._evalUrl = function(e) {
        return oe.ajax({
            url: e,
            type: "GET",
            dataType: "script",
            async: !1,
            global: !1,
            "throws": !0
        })
    }, oe.fn.extend({
        wrapAll: function(e) {
            if (oe.isFunction(e))
                return this.each(function(t) {
                    oe(this).wrapAll(e.call(this, t))
                });
            if (this[0]) {
                var t = oe(e, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && t.insertBefore(this[0]), t.map(function() {
                    for (var e = this; e.firstChild && 1 === e.firstChild.nodeType;)
                        e = e.firstChild;
                    return e
                }).append(this)
            }
            return this
        },
        wrapInner: function(e) {
            return this.each(oe.isFunction(e) ? function(t) {
                oe(this).wrapInner(e.call(this, t))
            } : function() {
                var t = oe(this), n = t.contents();
                n.length ? n.wrapAll(e) : t.append(e)
            })
        },
        wrap: function(e) {
            var t = oe.isFunction(e);
            return this.each(function(n) {
                oe(this).wrapAll(t ? e.call(this, n) : e)
            })
        },
        unwrap: function() {
            return this.parent().each(function() {
                oe.nodeName(this, "body") || oe(this).replaceWith(this.childNodes)
            }).end()
        }
    }), oe.expr.filters.hidden = function(e) {
        return e.offsetWidth <= 0 && e.offsetHeight <= 0 ||!re.reliableHiddenOffsets() && "none" === (e.style && e.style.display || oe.css(e, "display"))
    }, oe.expr.filters.visible = function(e) {
        return !oe.expr.filters.hidden(e)
    };
    var Vt = /%20/g, Jt = /\[\]$/, Yt = /\r?\n/g, Gt = /^(?:submit|button|image|reset|file)$/i, Qt = /^(?:input|select|textarea|keygen)/i;
    oe.param = function(e, t) {
        var n, r = [], i = function(e, t) {
            t = oe.isFunction(t) ? t() : null == t ? "" : t, r[r.length] = encodeURIComponent(e) + "=" + encodeURIComponent(t)
        };
        if (void 0 === t && (t = oe.ajaxSettings && oe.ajaxSettings.traditional), oe.isArray(e) || e.jquery&&!oe.isPlainObject(e)
            )oe.each(e, function() {
            i(this.name, this.value)
        });
        else 
            for (n in e)
                I(n, e[n], t, i);
        return r.join("&").replace(Vt, "+")
    }, oe.fn.extend({
        serialize: function() {
            return oe.param(this.serializeArray())
        },
        serializeArray: function() {
            return this.map(function() {
                var e = oe.prop(this, "elements");
                return e ? oe.makeArray(e) : this
            }).filter(function() {
                var e = this.type;
                return this.name&&!oe(this).is(":disabled") && Qt.test(this.nodeName)&&!Gt.test(e) && (this.checked ||!Le.test(e))
            }).map(function(e, t) {
                var n = oe(this).val();
                return null == n ? null : oe.isArray(n) ? oe.map(n, function(e) {
                    return {
                        name: t.name,
                        value: e.replace(Yt, "\r\n")
                    }
                }) : {
                    name: t.name,
                    value: n.replace(Yt, "\r\n")
                }
            }).get()
        }
    }), oe.ajaxSettings.xhr = void 0 !== e.ActiveXObject ? function() {
        return !this.isLocal && /^(get|post|head|put|delete|options)$/i.test(this.type) && X() || U()
    } : X;
    var Kt = 0, Zt = {}, en = oe.ajaxSettings.xhr();
    e.ActiveXObject && oe(e).on("unload", function() {
        for (var e in Zt)
            Zt[e](void 0, !0)
    }), re.cors=!!en && "withCredentials"in en, en = re.ajax=!!en, en && oe.ajaxTransport(function(e) {
        if (!e.crossDomain || re.cors) {
            var t;
            return {
                send: function(n, r) {
                    var i, o = e.xhr(), a=++Kt;
                    if (o.open(e.type, e.url, e.async, e.username, e.password), e.xhrFields)
                        for (i in e.xhrFields)
                            o[i] = e.xhrFields[i];
                    e.mimeType && o.overrideMimeType && o.overrideMimeType(e.mimeType), e.crossDomain || n["X-Requested-With"] || (n["X-Requested-With"] = "XMLHttpRequest");
                    for (i in n)
                        void 0 !== n[i] && o.setRequestHeader(i, n[i] + "");
                    o.send(e.hasContent && e.data || null), t = function(n, i) {
                        var s, l, u;
                        if (t && (i || 4 === o.readyState))
                            if (delete Zt[a], t = void 0, o.onreadystatechange = oe.noop, i)
                                4 !== o.readyState && o.abort();
                            else {
                                u = {}, s = o.status, "string" == typeof o.responseText && (u.text = o.responseText);
                                try {
                                    l = o.statusText
                                } catch (c) {
                                    l = ""
                                }
                                s ||!e.isLocal || e.crossDomain ? 1223 === s && (s = 204) : s = u.text ? 200 : 404
                            }
                        u && r(s, l, u, o.getAllResponseHeaders())
                    }, e.async ? 4 === o.readyState ? setTimeout(t) : o.onreadystatechange = Zt[a] = t : t()
                },
                abort: function() {
                    t && t(void 0, !0)
                }
            }
        }
    }), oe.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /(?:java|ecma)script/
        },
        converters: {
            "text script": function(e) {
                return oe.globalEval(e), e
            }
        }
    }), oe.ajaxPrefilter("script", function(e) {
        void 0 === e.cache && (e.cache=!1), e.crossDomain && (e.type = "GET", e.global=!1)
    }), oe.ajaxTransport("script", function(e) {
        if (e.crossDomain) {
            var t, n = me.head || oe("head")[0] || me.documentElement;
            return {
                send: function(r, i) {
                    t = me.createElement("script"), t.async=!0, e.scriptCharset && (t.charset = e.scriptCharset), t.src = e.url, t.onload = t.onreadystatechange = function(e, n) {
                        (n ||!t.readyState || /loaded|complete/.test(t.readyState)) && (t.onload = t.onreadystatechange = null, t.parentNode && t.parentNode.removeChild(t), t = null, n || i(200, "success"))
                    }, n.insertBefore(t, n.firstChild)
                },
                abort: function() {
                    t && t.onload(void 0, !0)
                }
            }
        }
    });
    var tn = [], nn = /(=)\?(?=&|$)|\?\?/;
    oe.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function() {
            var e = tn.pop() || oe.expando + "_" + Lt++;
            return this[e]=!0, e
        }
    }), oe.ajaxPrefilter("json jsonp", function(t, n, r) {
        var i, o, a, s = t.jsonp!==!1 && (nn.test(t.url) ? "url" : "string" == typeof t.data&&!(t.contentType || "").indexOf("application/x-www-form-urlencoded") && nn.test(t.data) && "data");
        return s || "jsonp" === t.dataTypes[0] ? (i = t.jsonpCallback = oe.isFunction(t.jsonpCallback) ? t.jsonpCallback() : t.jsonpCallback, s ? t[s] = t[s].replace(nn, "$1" + i) : t.jsonp!==!1 && (t.url += (Ht.test(t.url) ? "&" : "?") + t.jsonp + "=" + i), t.converters["script json"] = function() {
            return a || oe.error(i + " was not called"), a[0]
        }, t.dataTypes[0] = "json", o = e[i], e[i] = function() {
            a = arguments
        }, r.always(function() {
            e[i] = o, t[i] && (t.jsonpCallback = n.jsonpCallback, tn.push(i)), a && oe.isFunction(o) && o(a[0]), a = o = void 0
        }), "script") : void 0
    }), oe.parseHTML = function(e, t, n) {
        if (!e || "string" != typeof e)
            return null;
        "boolean" == typeof t && (n = t, t=!1), t = t || me;
        var r = fe.exec(e), i=!n && [];
        return r ? [t.createElement(r[1])] : (r = oe.buildFragment([e], t, i), i && i.length && oe(i).remove(), oe.merge([], r.childNodes))
    };
    var rn = oe.fn.load;
    oe.fn.load = function(e, t, n) {
        if ("string" != typeof e && rn)
            return rn.apply(this, arguments);
        var r, i, o, a = this, s = e.indexOf(" ");
        return s >= 0 && (r = e.slice(s, e.length), e = e.slice(0, s)), oe.isFunction(t) ? (n = t, t = void 0) : t && "object" == typeof t && (o = "POST"), a.length > 0 && oe.ajax({
            url: e,
            type: o,
            dataType: "html",
            data: t
        }).done(function(e) {
            i = arguments, a.html(r ? oe("<div>").append(oe.parseHTML(e)).find(r) : e)
        }).complete(n && function(e, t) {
            a.each(n, i || [e.responseText, t, e])
        }), this
    }, oe.expr.filters.animated = function(e) {
        return oe.grep(oe.timers, function(t) {
            return e === t.elem
        }).length
    };
    var on = e.document.documentElement;
    oe.offset = {
        setOffset: function(e, t, n) {
            var r, i, o, a, s, l, u, c = oe.css(e, "position"), d = oe(e), f = {};
            "static" === c && (e.style.position = "relative"), s = d.offset(), o = oe.css(e, "top"), l = oe.css(e, "left"), u = ("absolute" === c || "fixed" === c) && oe.inArray("auto", [o, l])>-1, u ? (r = d.position(), a = r.top, i = r.left) : (a = parseFloat(o) || 0, i = parseFloat(l) || 0), oe.isFunction(t) && (t = t.call(e, n, s)), null != t.top && (f.top = t.top - s.top + a), null != t.left && (f.left = t.left - s.left + i), "using"in t ? t.using.call(e, f) : d.css(f)
        }
    }, oe.fn.extend({
        offset: function(e) {
            if (arguments.length)
                return void 0 === e ? this : this.each(function(t) {
                oe.offset.setOffset(this, e, t)
            });
            var t, n, r = {
                top: 0,
                left: 0
            }, i = this[0], o = i && i.ownerDocument;
            return o ? (t = o.documentElement, oe.contains(t, i) ? (typeof i.getBoundingClientRect !== Ne && (r = i.getBoundingClientRect()), n = V(o), {
                top: r.top + (n.pageYOffset || t.scrollTop) - (t.clientTop || 0),
                left: r.left + (n.pageXOffset || t.scrollLeft) - (t.clientLeft || 0)
            }) : r) : void 0
        },
        position: function() {
            if (this[0]) {
                var e, t, n = {
                    top: 0,
                    left: 0
                }, r = this[0];
                return "fixed" === oe.css(r, "position") ? t = r.getBoundingClientRect() : (e = this.offsetParent(), t = this.offset(), oe.nodeName(e[0], "html") || (n = e.offset()), n.top += oe.css(e[0], "borderTopWidth", !0), n.left += oe.css(e[0], "borderLeftWidth", !0)), {
                    top: t.top - n.top - oe.css(r, "marginTop", !0),
                    left: t.left - n.left - oe.css(r, "marginLeft", !0)
                }
            }
        },
        offsetParent: function() {
            return this.map(function() {
                for (var e = this.offsetParent || on; e&&!oe.nodeName(e, "html") && "static" === oe.css(e, "position");)
                    e = e.offsetParent;
                return e || on
            })
        }
    }), oe.each({
        scrollLeft: "pageXOffset",
        scrollTop: "pageYOffset"
    }, function(e, t) {
        var n = /Y/.test(t);
        oe.fn[e] = function(r) {
            return je(this, function(e, r, i) {
                var o = V(e);
                return void 0 === i ? o ? t in o ? o[t] : o.document.documentElement[r] : e[r] : void(o ? o.scrollTo(n ? oe(o).scrollLeft() : i, n ? i : oe(o).scrollTop()) : e[r] = i);

            }, e, r, arguments.length, null)
        }
    }), oe.each(["top", "left"], function(e, t) {
        oe.cssHooks[t] = k(re.pixelPosition, function(e, n) {
            return n ? (n = nt(e, t), it.test(n) ? oe(e).position()[t] + "px" : n) : void 0
        })
    }), oe.each({
        Height: "height",
        Width: "width"
    }, function(e, t) {
        oe.each({
            padding: "inner" + e,
            content: t,
            "": "outer" + e
        }, function(n, r) {
            oe.fn[r] = function(r, i) {
                var o = arguments.length && (n || "boolean" != typeof r), a = n || (r===!0 || i===!0 ? "margin" : "border");
                return je(this, function(t, n, r) {
                    var i;
                    return oe.isWindow(t) ? t.document.documentElement["client" + e] : 9 === t.nodeType ? (i = t.documentElement, Math.max(t.body["scroll" + e], i["scroll" + e], t.body["offset" + e], i["offset" + e], i["client" + e])) : void 0 === r ? oe.css(t, n, a) : oe.style(t, n, r, a)
                }, t, o ? r : void 0, o, null)
            }
        })
    }), oe.fn.size = function() {
        return this.length
    }, oe.fn.andSelf = oe.fn.addBack, "function" == typeof define && define.amd && define("jquery", [], function() {
        return oe
    });
    var an = e.jQuery, sn = e.$;
    return oe.noConflict = function(t) {
        return e.$ === oe && (e.$ = sn), t && e.jQuery === oe && (e.jQuery = an), oe
    }, typeof t === Ne && (e.jQuery = e.$ = oe), oe
});;
!function(e, t) {
    e(t);
    e.fn.Xslider = function(i) {
        function n() {
            if (r.isCheckShow) {
                var i = e(t).scrollTop(), n = i + e(t).height(), s = e(r.content_box), c = s.position().top;
                posb = c + s.height(), (c >= i && n > c || posb > i && posb < n) && a()
            } else 
                a()
        }
        function a() {
            switch (s(), l >= f.length && (l = 0), x.removeClass(r.active_class).eq(l).addClass(r.active_class), r.effect) {
            case"scrollx":
                d.width(f.length * f.outerWidth());
                var e = v - f.width() * l;
                d.stop().animate({
                    left: e
                }, r.speed);
                break;
            case"scrolly":
                var t = _ - f.height() * l;
                d.stop().animate({
                    top: t
                }, r.speed);
                break;
            case"fade":
                f.eq(h).stop(!0, !0).fadeOut(r.speed).end().eq(l).stop(!0, !0).fadeIn(r.speed);
                break;
            case"none":
                f.hide().eq(l).show()
            }
            "function" == typeof r.callback_fn && r.callback_fn(l), h = l, l++, w()
        }
        function s() {
            if (r.load_type) {
                var t = f.eq(l).find(r.load_type);
                switch (r.load_type) {
                case"img":
                    t.each(function() {
                        var t = e(this);
                        t.attr("data-url") && t.attr("src", t.data("url")).removeAttr("data-url")
                    });
                    break;
                case"textarea":
                    t.css("visibility", "hidden"), "none" != t.css("display") && t.before(t.val()).hide()
                }
            }
        }
        function c() {
            clearInterval(m)
        }
        function o() {
            r.auto && (m = setInterval(n, r.space))
        }
        var r = e.extend({
            effect: "scrollx",
            speed: 300,
            space: 6e3,
            auto: !1,
            trigger: "mouseover",
            content_box: ".contents",
            content_tag: "li",
            switcher_box: ".switchers",
            switcher_tag: "li",
            active_class: "active",
            prev: ".prev",
            next: ".next",
            rand: !1,
            index: "",
            load_type: !1,
            callback_fn: null,
            isCheckShow: !0
        }, i), l = 1, h = 0, d = e(this).find(r.content_box), f = d.find(r.content_tag), p = e(this).find(r.switcher_box);
        if (d.length < 1)
            return !1;
        var u = d.position(), v = u.left, _ = u.top;
        if (r.rand && (l = Math.floor(Math.random() * f.length), n()), "fade" == r.effect && e.each(f, function(t, i) {
            e(this).css(0 == t ? {
                position: "absolute",
                "z-index": 9
            } : {
                position: "absolute",
                "z-index": 1
            })
        }), "list" === r.index) {
            for (var g = "", b = 1; b <= f.length; b++)
                g += 1 === b ? "<" + r.switcher_tag + ' class="' + r.active_class + '" >' + b + "</" + r.switcher_tag + ">" : "<" + r.switcher_tag + ">" + b + "</" + r.switcher_tag + ">";
            p.html(g)
        }
        var x = p.find(r.switcher_tag), w = function() {
            e(r.index).length > 0 && e(r.index).html('<em class="index">' + l + '</em>/<em class="count">' + f.length + "</em>")
        };
        if (w(), r.auto)
            var m = setInterval(n, r.space);
        return x.bind(r.trigger, function() {
            c(), l = e(this).index(), n(), o()
        }), e(this).find(r.prev).click(function() {
            c(), h && (l=--h, n(), o())
        }), e(this).find(r.next).click(function() {
            c(), n(), o()
        }), d.hover(c, o), this
    }
}(jQuery, window);;
if (function(e, t) {
    "use strict";
    function n() {}
    function o(e, t) {
        if (e) {
            "object" == typeof e && (e = [].slice.call(e));
            for (var n = 0, o = e.length; o > n; n++)
                t.call(e, e[n], n)
        }
    }
    function r(e, n) {
        var o = Object.prototype.toString.call(n).slice(8, - 1);
        return n !== t && null !== n && o === e
    }
    function a(e) {
        return r("Function", e)
    }
    function i(e) {
        return r("Array", e)
    }
    function l(e) {
        var t = e.split("/"), n = t[t.length - 1], o = n.indexOf("?");
        return - 1 !== o ? n.substring(0, o) : n
    }
    function c(e) {
        e = e || n, e._done || (e(), e._done = 1)
    }
    function u(e) {
        var t = {};
        if ("object" == typeof e)
            for (var n in e)
                e[n] && (t = {
                    name: n,
                    url: e[n]
                });
        else 
            t = {
                name: l(e),
                url: e
            };
        var o = L[t.name];
        return o && o.url === t.url ? o : (L[t.name] = t, t)
    }
    function s(e) {
        e = e || L;
        for (var t in e)
            if (e.hasOwnProperty(t) && e[t].state !== x)
                return !1;
        return !0
    }
    function d(e) {
        e.state = _, o(e.onpreload, function(e) {
            e.call()
        })
    }
    function f(e, n) {
        e.state === t && (e.state = O, e.onpreload = [], m({
            url: e.url,
            type: "cache"
        }, function() {
            d(e)
        }))
    }
    function p(e, t) {
        return t = t || n, e.state === x ? void t() : e.state === K ? void k.ready(e.name, t) : e.state === O ? void e.onpreload.push(function() {
            p(e, t)
        }) : (e.state = K, void m(e, function() {
            e.state = x, t(), o(j[e.name], function(e) {
                c(e)
            }), h && s() && o(j.ALL, function(e) {
                c(e)
            })
        }))
    }
    function m(t, o) {
        function r(t) {
            t = t || e.event, i.onload = i.onreadystatechange = i.onerror = null, o()
        }
        function a(t) {
            t = t || e.event, ("load" === t.type || /loaded|complete/.test(i.readyState) && (!b.documentMode || b.documentMode < 9)) && (i.onload = i.onreadystatechange = i.onerror = null, o())
        }
        o = o || n;
        var i;
        /\.css[^\.]*$/.test(t.url) ? (i = b.createElement("link"), i.type = "text/" + (t.type || "css"), i.rel = "stylesheet", i.href = t.url) : (i = b.createElement("script"), i.type = "text/" + (t.type || "javascript"), i.src = t.url), i.onload = i.onreadystatechange = a, i.onerror = r, i.async=!1, i.defer=!1;
        var l = b.head || b.getElementsByTagName("head")[0];
        l.insertBefore(i, l.lastChild)
    }
    function y() {
        return b.body ? void(h || (h=!0, o(w, function(e) {
            c(e)
        }))) : (e.clearTimeout(k.readyTimeout), void(k.readyTimeout = e.setTimeout(y, 50)))
    }
    function v() {
        b.addEventListener ? (b.removeEventListener("DOMContentLoaded", v, !1), y()) : "complete" === b.readyState && (b.detachEvent("onreadystatechange", v), y())
    }
    var g, h, b = e.document, w = [], E = [], j = {}, L = {}, S = "async"in b.createElement("script") || "MozAppearance"in b.documentElement.style || e.opera, T = e.head_conf && e.head_conf.head || "Kee", k = e[T] = e[T] || function() {
        k.ready.apply(null, arguments)
    }, O = 1, _ = 2, K = 3, x = 4;
    if (k.load = S ? function() {
        var e = arguments, t = e[e.length - 1], n = {};
        return a(t) || (t = null), o(e, function(o, r) {
            o !== t && (o = u(o), n[o.name] = o, p(o, t && r === e.length - 2 ? function() {
                s(n) && c(t)
            } : null))
        }), k
    } : function() {
        var e = arguments, t = [].slice.call(e, 1), n = t[0];
        return g ? (n ? (o(t, function(e) {
            a(e) || f(u(e))
        }), p(u(e[0]), a(n) ? n : function() {
            k.load.apply(null, t)
        })) : p(u(e[0])), k) : (E.push(function() {
            k.load.apply(null, e)
        }), k)
    }, k.js = k.load, k.test = function(e, t, o, r) {
        var a = "object" == typeof e ? e: {
            test: e,
            success: t ? i(t) ? t: [t]: !1,
            failure: o ? i(o) ? o: [o]: !1,
            callback: r || n
        }, l=!!a.test;
        return l && a.success ? (a.success.push(a.callback), k.load.apply(null, a.success)) : !l && a.failure ? (a.failure.push(a.callback), k.load.apply(null, a.failure)) : r(), k
    }, k.ready = function(e, t) {
        if (e === b)
            return h ? c(t) : w.push(t), k;
        if (a(e) && (t = e, e = "ALL"), "string" != typeof e ||!a(t))
            return k;
        var n = L[e];
        if (n && n.state === x || "ALL" === e && s() && h)
            return c(t), k;
        var o = j[e];
        return o ? o.push(t) : o = j[e] = [t], k
    }, k.ready(b, function() {
        s() && o(j.ALL, function(e) {
            c(e)
        }), k.feature && k.feature("domloaded", !0)
    }), "complete" === b.readyState)
        y();
    else if (b.addEventListener)
        b.addEventListener("DOMContentLoaded", v, !1), e.addEventListener("load", y, !1);
    else {
        b.attachEvent("onreadystatechange", v), e.attachEvent("onload", y);
        var A=!1;
        try {
            A = null == e.frameElement && b.documentElement
        } catch (C) {}
        A && A.doScroll&&!function D() {
            if (!h) {
                try {
                    A.doScroll("left")
                } catch (t) {
                    return e.clearTimeout(k.readyTimeout), void(k.readyTimeout = e.setTimeout(D, 50))
                }
                y()
            }
        }()
    }
    setTimeout(function() {
        g=!0, o(E, function(e) {
            e()
        })
    }, 300);
    var M = navigator.userAgent.toLowerCase();
    M = /(webkit)[ \/]([\w.]+)/.exec(M) || /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(M) || /(msie) ([\w.]+)/.exec(M) ||!/compatible/.test(M) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(M) || [], "msie" == M[1] && (M[1] = "ie", M[2] = document.documentMode || M[2]), k.browser = {
        version: M[2]
    }, k.browser[M[1]]=!0, k.browser.ie && o("abbr|article|aside|audio|canvas|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video".split("|"), function(e) {
        b.createElement(e)
    })
}(window), !window.console) {
    window.console = {};
    for (var funs = ["profiles", "memory", "_commandLineAPI", "debug", "error", "info", "log", "warn", "dir", "dirxml", "trace", "assert", "count", "markTimeline", "profile", "profileEnd", "time", "timeEnd", "timeStamp", "group", "groupCollapsed", "groupEnd"], i = 0; i < funs.length; i++)
        console[funs[i]] = function() {}
}
Kee.ready(function() {}), function(e) {
    var t = e.GV.CDN_ROOT || "http://cdn.shikee.com/", n = e.GV.JS_VERSION.replace(/(\s)/g, "_") || "1.1", o = e.GV.CSS_VERSION.replace(/(\s)/g, "_") || "1.1", r = {
        jquery: "common/libs/jquery/jquery-1.10.2.min",
        artDialog: "common/libs/artdialog/artdialog-min",
        artDialogTools: "common/libs/artdialog/plugins/iframe-tools",
        tipMes: "common/libs/tipmes/tipmes",
        skStar: "common/libs/star/jquery.star"
    }, a = {
        artDialog: "common/libs/artdialog/skins/default",
        tipMes: "common/libs/tipmes/style"
    };
    for (var i in r)
        r.hasOwnProperty(i) && (r[i] = t + r[i] + ".js?v=" + n);
    for (var i in a)
        a.hasOwnProperty(i) && (a[i] = t + a[i] + ".css?v=" + o);
    e.Kee = e.Kee || {}, Kee.css = function(e, t) {
        var n = a[e] ? a[e]: e, o = document.createElement("link");
        o.rel = "stylesheet", o.href = n, o.onload = o.onreadystatechange = function() {
            var e = o.readyState;
            !t || t.done || e&&!/loaded|complete/.test(e) || (t.done=!0, t())
        }, document.getElementsByTagName("head")[0].appendChild(o)
    }, Kee.use = function() {
        for (var e = arguments, t = e.length, n = 0; t > n; n++)
            "string" == typeof e[n] && r[e[n]] && (e[n] = r[e[n]]);
        Kee.js.apply(null, e)
    };
    var l = {};
    Kee.tmpl = function(e, t) {
        var n = /\W/.test(e) ? new Function("obj", "var p=[],print=function(){p.push.apply(p,arguments);};with(obj){p.push('" + e.replace(/[\r\t\n]/g, " ").split("<%").join(" ").replace(/((^|%>)[^\t]*)'/g, "$1\r").replace(/\t=(.*?)%>/g, "',$1,'").split(" ").join("');").split("%>").join("p.push('").split("\r").join("\\'") + "');}return p.join('');"): l[e] = l[e] || tmpl(e);
        return t ? n(t) : n
    }, Kee.Util = {}
}(window);;
$(function() {
    var e = window.location.href.split(".")[1];
    window.domain_user = "http://user." + e + ".com/", window.domain_hlzf = "http://user." + e + ".com/go/hlpay", window.domain_help = "http://help." + e + ".com/", window.domain_bbs = "http://bbs." + e + ".com/", window.domain_list = "http://list." + e + ".com/", window.domain_trailer = "http://trailer." + e + ".com/", window.domain_pass = "http://pass." + e + ".com/", window.domain_zhs_www = "http://www.zhonghuasuan.com", window.domain_down_app = "http://list." + e + ".com/special/mini_report", window.domain_zhs_list = "http://list.zhonghuasuan.com", window.domain_report = "http://report." + e + ".com/", window.domain_reg = "http://reg." + e + ".com/", window.domain_login = "http://login." + e + ".com/", window.domain_www = "http://www." + e + ".com/", sk.init()
});
var sk = function() {
    var e = location.host.replace(/.*(\.\w+\.com)/, "$1");
    return {
        init: function() {
            this.url.reg = window.domain_reg + "reg/doreg/?uType=1", $(function() {
                sk.user.init(), $("#j_topNavManuHover").hover(function() {
                    $(this).addClass("top-nm-hover")
                }, function() {
                    $(this).removeClass("top-nm-hover")
                })
            })
        },
        site: function(o) {
            return "http://" + o + e + "/"
        },
        url: {
            reg: ""
        },
        query: function(e) {
            var o = location.search.match(new RegExp("[?&]" + e + "=([^&]+)", "i"));
            return null == o || o.length < 1 ? "" : o[1]
        },
        cookie: function(e, o, n) {
            if ("undefined" == typeof o) {
                var t = null;
                if (document.cookie && "" != document.cookie)
                    for (var i = document.cookie.split(";"), a = 0; a < i.length; a++) {
                        var r = jQuery.trim(i[a]);
                        if (r.substring(0, e.length + 1) == e + "=") {
                            t = decodeURIComponent(r.substring(e.length + 1));
                            break
                        }
                    }
                return t
            }
            n = n || {}, null === o && (o = "", n = $.extend({}, n), n.expires =- 1);
            var s = "";
            if (n.expires && ("number" == typeof n.expires || n.expires.toUTCString)) {
                var d;
                "number" == typeof n.expires ? (d = new Date, d.setTime(d.getTime() + 24 * n.expires * 60 * 60 * 1e3)) : d = n.expires, s = "; expires=" + d.toUTCString()
            }
            var p = n.path ? "; path=" + n.path: "", l = n.domain ? "; domain=" + n.domain: "", h = n.secure ? "; secure": "";
            document.cookie = [e, "=", encodeURIComponent(o), s, p, l, h].join("")
        }
    }
}();
sk.user = function() {
    var e;
    return {
        init: function() {
            e = $("#topbar");
            var o = this.info();
            e.find(".top-nav-other").prepend('<a href="' + domain_help + 'shike">帮助中心</a>'), e.find(".top-nav-other").prepend('<a href="' + domain_hlzf + '">互联支付</a>'), e.find(".top-nav-other").prepend('<a href="' + domain_www + '">网站首页</a>'), e.find(".top-nav-other").prepend('<div class="qq_group" id="J_qqGroup" target="_blank" href="javascript:;"><span class="qq_group_icon"></span>QQ交流群<div class="qq_group_code" id="J_group_code"><i class="tri"></i><a href="http://shang.qq.com/wpa/qunwpa?idkey=fe0b9b5828276d553f91a683d2f49e2cc6e85dcbb9c5007bd4b2274b3200e694"  target="_blank">群号<i>(474134996)</i></a><p><span class="code-img-one"></span></p></div></div>');
            var n = '<dt class="none-border">免费试用</dt><dd><a target="_blank" href="' + domain_list + 'list-1.html?type=1">试用专区</a><a target="_blank" href="' + domain_trailer + 'list-1.html?type=1">预告专区</a></dd><dt class="none-border">试用报告</dt><dd><a target="_blank" href="' + domain_report + 'list-1.html?type=1">报告专区</a></dd>';
            e.find(".top-nm-bd").prepend(n);
            var t = $("#J_group_code"), i = "#J_qqGroup,#J_group_code", a = null;
            if ($(i).on("mouseover", function() {
                clearTimeout(a), t.show()
            }), $(i).on("mouseout", function() {
                a = setTimeout(function() {
                    t.hide()
                }, 1500)
            }), !o)
                return e.find(".loginInfo").append('您好，欢迎来到说蘑菇！请 [<a target="_self" href="' + domain_login + '">登录</a>] [<a target="_self" href="' + domain_reg + '">免费注册</a>] '), void $(".sy-icon").attr("href", window.domain_login);
            $(".sy-icon").attr("href", window.domain_user);
            var r = domain_user, s = [], d = Math.round((new Date).getTime() / 1e3);
            s.push('您好，<a title="进入后台" href="', r, "?time=", d, '">', o.name, "</a>"), 1 == o.type ? s.push(' <span class="topbar-tipMsg">您有未读提醒<a href="', r, 'buyer/message/">(<em>0</em>)</a>条</span>') : 2 == o.type && s.push(' <span class="topbar-tipMsg">您有未读提醒<a href="', r, 'seller/message/">(<em>0</em>)</a>条</span>'), s.push(' <a href="', domain_login, "logout/?to=", window.location.href, '" target="_self">[ 退出 ]</a>'), e.find(".loginInfo").html(s.join("")), this.updateMsg(), 1 == o.type ? e.find(".top-nav-other").prepend('<a href="' + domain_user + 'buyer/report/mylist">我的试用报告</a>') : 2 == o.type && e.find(".top-nav-other").prepend('<a href="' + domain_user + 'seller/tryings/add_try_base">发布试用品</a>'), e.find(".top-nav-other").prepend('<a href="' + r + "?time=" + d + '">我的说蘑菇</a>')
        },
        info: function() {
            var e = sk.cookie("shuomogu");
            if (!e || 3 != e.split("|").length)
                return null;
            var o = e.split("|");
            return {
                name: o[0],
                type: o[1]
            }
        },
        updateMsg: function() {
            $.getJSON(sk.site("www") + "get_stat/?type=msg&callback=?", function(o) {
                o.error || (o.msg_unread > 0 ? e.find(".topbar-tipMsg a").html("(<strong class='fc-f4'>" + o.msg_unread + "</strong>)") : e.find(".topbar-tipMsg a em").html(o.msg_unread))
            })
        },
        login: function(e) {
            var o = domain_login + "home/win/?to=" + (e || location.href);
            Kee.use("artDialog", "artDialogTools", function() {
                art.dialog.open(o, {
                    id: "win-login",
                    lock: !0,
                    fixed: !0,
                    drag: !1,
                    opacity: .6,
                    width: "300px",
                    height: "250px",
                    title: "欢迎登录说蘑菇"
                })
            })
        }
    }
}();;
!function(s) {
    var a = s("#J_sch_wrap"), t = s("#formTop"), e = s("#keySearch"), o = s("#J_search"), i = t.find(".sch-tip");
    i.hover(function() {
        s(this).addClass("sch-tip-hover"), i.find("a").on("click", function() {
            switch (s(this).text()) {
            case"试用品":
                t.attr({
                    action: domain_list
                }), e.attr("name", "keyword"), o.attr({
                    name: "topSearch",
                    value: "1"
                });
                break;
            case"众划算":
                t.attr({
                    action: domain_zhs_list + "/search/"
                }), e.attr("name", "key"), o.attr({
                    name: "type",
                    value: "goods"
                });
                break;
            case"店铺":
                t.attr({
                    action: domain_list + "shop"
                }), e.attr("name", "key")
            }
            i.prepend(s(this)).removeClass("sch-tip-hover"), s(this).addClass("sel").siblings().removeClass("sel"), e.focus()
        })
    }, function() {
        s(this).removeClass("sch-tip-hover")
    }), a.on("focusin", function() {
        s(this).addClass("search-wrap-focus")
    }).on("focusout", function() {
        s(this).hasClass("search-wrap-focus") && i.hasClass("sch-tip-hover") || s(this).removeClass("search-wrap-focus")
    }), t.submit(function() {
        return "" == s.trim(e.val()) ? (alert("请输入搜索关键字"), e.focus(), !1) : void 0
    }), s("#hidLogoinUid").length > 0 && e.val(decodeURIComponent(sk.query("key"))), - [1, ] || window.XMLHttpRequest || s("#J_subNav").hover(function() {
        s(this).find(".sub-nav-bd").show(), s(this).find(".arrow").addClass("arrow-hover")
    }, function() {
        s(this).find(".sub-nav-bd").hide(), s(this).find(".arrow").removeClass("arrow-hover")
    }), s("#is_flash").on("click", function() {
        var a = location.href, t = s(this).prop("checked");
        a = a.replace(/&is_flash=[0|1]/, ""), t && (a += "&is_flash=1"), location.replace(a)
    })
}(jQuery);;
!function(e) {
    "use strict";
    function t(t) {
        var r = t.data;
        t.isDefaultPrevented() || (t.preventDefault(), e(this).ajaxSubmit(r))
    }
    function r(t) {
        var r = t.target, a = e(r);
        if (!a.is("[type=submit],[type=image]")) {
            var n = a.closest("[type=submit]");
            if (0 === n.length)
                return;
            r = n[0]
        }
        var i = this;
        if (i.clk = r, "image" == r.type)
            if (void 0 !== t.offsetX)
                i.clk_x = t.offsetX, i.clk_y = t.offsetY;
            else if ("function" == typeof e.fn.offset) {
                var o = a.offset();
                i.clk_x = t.pageX - o.left, i.clk_y = t.pageY - o.top
            } else 
                i.clk_x = t.pageX - r.offsetLeft, i.clk_y = t.pageY - r.offsetTop;
        setTimeout(function() {
            i.clk = i.clk_x = i.clk_y = null
        }, 100)
    }
    function a() {
        if (e.fn.ajaxSubmit.debug) {
            var t = "[jquery.form] " + Array.prototype.join.call(arguments, "");
            window.console && window.console.log ? window.console.log(t) : window.opera && window.opera.postError && window.opera.postError(t)
        }
    }
    var n = {};
    n.fileapi = void 0 !== e("<input type='file'/>").get(0).files, n.formdata = void 0 !== window.FormData;
    var i=!!e.fn.prop;
    e.fn.attr2 = function() {
        if (!i)
            return this.attr.apply(this, arguments);
        var e = this.prop.apply(this, arguments);
        return e && e.jquery || "string" == typeof e ? e : this.attr.apply(this, arguments)
    }, e.fn.ajaxSubmit = function(t) {
        function r(r) {
            var a, n, i = e.param(r, t.traditional).split("&"), o = i.length, s = [];
            for (a = 0; o > a; a++)
                i[a] = i[a].replace(/\+/g, " "), n = i[a].split("="), s.push([decodeURIComponent(n[0]), decodeURIComponent(n[1])]);
            return s
        }
        function o(a) {
            for (var n = new FormData, i = 0; a.length > i; i++)
                n.append(a[i].name, a[i].value);
            if (t.extraData) {
                var o = r(t.extraData);
                for (i = 0; o.length > i; i++)
                    o[i] && n.append(o[i][0], o[i][1])
            }
            t.data = null;
            var s = e.extend(!0, {}, e.ajaxSettings, t, {
                contentType: !1,
                processData: !1,
                cache: !1,
                type: u || "POST"
            });
            t.uploadProgress && (s.xhr = function() {
                var e = jQuery.ajaxSettings.xhr();
                return e.upload && e.upload.addEventListener("progress", function(e) {
                    var r = 0, a = e.loaded || e.position, n = e.total;
                    e.lengthComputable && (r = Math.ceil(100 * (a / n))), t.uploadProgress(e, a, n, r)
                }, !1), e
            }), s.data = null;
            var l = s.beforeSend;
            return s.beforeSend = function(e, t) {
                t.data = n, l && l.call(this, e, t)
            }, e.ajax(s)
        }
        function s(r) {
            function n(e) {
                var t = null;
                try {
                    e.contentWindow && (t = e.contentWindow.document)
                } catch (r) {
                    a("cannot get iframe.contentWindow document: " + r)
                }
                if (t)
                    return t;
                try {
                    t = e.contentDocument ? e.contentDocument : e.document
                } catch (r) {
                    a("cannot get iframe.contentDocument: " + r), t = e.document
                }
                return t
            }
            function o() {
                function t() {
                    try {
                        var e = n(g).readyState;
                        a("state = " + e), e && "uninitialized" == e.toLowerCase() && setTimeout(t, 50)
                    } catch (r) {
                        a("Server abort: ", r, " (", r.name, ")"), s(D), j && clearTimeout(j), j = void 0
                    }
                }
                var r = f.attr2("target"), i = f.attr2("action");
                w.setAttribute("target", d), u || w.setAttribute("method", "POST"), i != m.url && w.setAttribute("action", m.url), m.skipEncodingOverride || u&&!/post/i.test(u) || f.attr({
                    encoding: "multipart/form-data",
                    enctype: "multipart/form-data"
                }), m.timeout && (j = setTimeout(function() {
                    T=!0, s(k)
                }, m.timeout));
                var o = [];
                try {
                    if (m.extraData)
                        for (var l in m.extraData)
                            m.extraData.hasOwnProperty(l) && o.push(e.isPlainObject(m.extraData[l]) && m.extraData[l].hasOwnProperty("name") && m.extraData[l].hasOwnProperty("value") ? e('<input type="hidden" name="' + m.extraData[l].name + '">').val(m.extraData[l].value).appendTo(w)[0] : e('<input type="hidden" name="' + l + '">').val(m.extraData[l]).appendTo(w)[0]);
                    m.iframeTarget || (v.appendTo("body"), g.attachEvent ? g.attachEvent("onload", s) : g.addEventListener("load", s, !1)), setTimeout(t, 15);
                    try {
                        w.submit()
                    } catch (c) {
                        var p = document.createElement("form").submit;
                        p.apply(w)
                    }
                } finally {
                    w.setAttribute("action", i), r ? w.setAttribute("target", r) : f.removeAttr("target"), e(o).remove()
                }
            }
            function s(t) {
                if (!x.aborted&&!F) {
                    if (M = n(g), M || (a("cannot access response document"), t = D), t === k && x)
                        return x.abort("timeout"), void S.reject(x, "timeout");
                    if (t == D && x)
                        return x.abort("server abort"), void S.reject(x, "error", "server abort");
                    if (M && M.location.href != m.iframeSrc || T) {
                        g.detachEvent ? g.detachEvent("onload", s) : g.removeEventListener("load", s, !1);
                        var r, i = "success";
                        try {
                            if (T)
                                throw "timeout";
                            var o = "xml" == m.dataType || M.XMLDocument || e.isXMLDoc(M);
                            if (a("isXml=" + o), !o && window.opera && (null === M.body ||!M.body.innerHTML)&&--O)
                                return a("requeing onLoad callback, DOM not available"), void setTimeout(s, 250);
                            var u = M.body ? M.body: M.documentElement;
                            x.responseText = u ? u.innerHTML : null, x.responseXML = M.XMLDocument ? M.XMLDocument : M, o && (m.dataType = "xml"), x.getResponseHeader = function(e) {
                                var t = {
                                    "content-type": m.dataType
                                };
                                return t[e]
                            }, u && (x.status = Number(u.getAttribute("status")) || x.status, x.statusText = u.getAttribute("statusText") || x.statusText);
                            var l = (m.dataType || "").toLowerCase(), c = /(json|script|text)/.test(l);
                            if (c || m.textarea) {
                                var f = M.getElementsByTagName("textarea")[0];
                                if (f)
                                    x.responseText = f.value, x.status = Number(f.getAttribute("status")) || x.status, x.statusText = f.getAttribute("statusText") || x.statusText;
                                else if (c) {
                                    var d = M.getElementsByTagName("pre")[0], h = M.getElementsByTagName("body")[0];
                                    d ? x.responseText = d.textContent ? d.textContent : d.innerText : h && (x.responseText = h.textContent ? h.textContent : h.innerText)
                                }
                            } else 
                                "xml" == l&&!x.responseXML && x.responseText && (x.responseXML = X(x.responseText));
                            try {
                                L = _(x, l, m)
                            } catch (b) {
                                i = "parsererror", x.error = r = b || i
                            }
                        } catch (b) {
                            a("error caught: ", b), i = "error", x.error = r = b || i
                        }
                        x.aborted && (a("upload aborted"), i = null), x.status && (i = x.status >= 200 && 300 > x.status || 304 === x.status ? "success" : "error"), "success" === i ? (m.success && m.success.call(m.context, L, "success", x), S.resolve(x.responseText, "success", x), p && e.event.trigger("ajaxSuccess", [x, m])) : i && (void 0 === r && (r = x.statusText), m.error && m.error.call(m.context, x, i, r), S.reject(x, "error", r), p && e.event.trigger("ajaxError", [x, m, r])), p && e.event.trigger("ajaxComplete", [x, m]), p&&!--e.active && e.event.trigger("ajaxStop"), m.complete && m.complete.call(m.context, x, i), F=!0, m.timeout && clearTimeout(j), setTimeout(function() {
                            m.iframeTarget || v.remove(), x.responseXML = null
                        }, 100)
                    }
                }
            }
            var l, c, m, p, d, v, g, x, b, y, T, j, w = f[0], S = e.Deferred();
            if (r)
                for (c = 0; h.length > c; c++)
                    l = e(h[c]), i ? l.prop("disabled", !1) : l.removeAttr("disabled");
            if (m = e.extend(!0, {}, e.ajaxSettings, t), m.context = m.context || m, d = "jqFormIO" + (new Date).getTime(), m.iframeTarget ? (v = e(m.iframeTarget), y = v.attr2("name"), y ? d = y : v.attr2("name", d)) : (v = e('<iframe name="' + d + '" src="' + m.iframeSrc + '" />'), v.css({
                position: "absolute",
                top: "-1000px",
                left: "-1000px"
            })), g = v[0], x = {
                aborted: 0,
                responseText: null,
                responseXML: null,
                status: 0,
                statusText: "n/a",
                getAllResponseHeaders: function() {},
                getResponseHeader: function() {},
                setRequestHeader: function() {},
                abort: function(t) {
                    var r = "timeout" === t ? "timeout": "aborted";
                    a("aborting upload... " + r), this.aborted = 1;
                    try {
                        g.contentWindow.document.execCommand && g.contentWindow.document.execCommand("Stop")
                    } catch (n) {}
                    v.attr("src", m.iframeSrc), x.error = r, m.error && m.error.call(m.context, x, r, t), p && e.event.trigger("ajaxError", [x, m, r]), m.complete && m.complete.call(m.context, x, r)
                }
            }, p = m.global, p && 0 === e.active++&&e.event.trigger("ajaxStart"), p && e.event.trigger("ajaxSend", [x, m]), m.beforeSend && m.beforeSend.call(m.context, x, m)===!1)return m.global && e.active--, S.reject(), S;
            if (x.aborted)
                return S.reject(), S;
            b = w.clk, b && (y = b.name, y&&!b.disabled && (m.extraData = m.extraData || {}, m.extraData[y] = b.value, "image" == b.type && (m.extraData[y + ".x"] = w.clk_x, m.extraData[y + ".y"] = w.clk_y)));
            var k = 1, D = 2, A = e("meta[name=csrf-token]").attr("content"), E = e("meta[name=csrf-param]").attr("content");
            E && A && (m.extraData = m.extraData || {}, m.extraData[E] = A), m.forceSync ? o() : setTimeout(o, 10);
            var L, M, F, O = 50, X = e.parseXML || function(e, t) {
                return window.ActiveXObject ? (t = new ActiveXObject("Microsoft.XMLDOM"), t.async = "false", t.loadXML(e)) : t = (new DOMParser).parseFromString(e, "text/xml"), t && t.documentElement && "parsererror" != t.documentElement.nodeName ? t : null
            }, C = e.parseJSON || function(e) {
                return window.eval("(" + e + ")")
            }, _ = function(t, r, a) {
                var n = t.getResponseHeader("content-type") || "", i = "xml" === r ||!r && n.indexOf("xml") >= 0, o = i ? t.responseXML: t.responseText;
                return i && "parsererror" === o.documentElement.nodeName && e.error && e.error("parsererror"), a && a.dataFilter && (o = a.dataFilter(o, r)), "string" == typeof o && ("json" === r ||!r && n.indexOf("json") >= 0 ? o = C(o) : ("script" === r ||!r && n.indexOf("javascript") >= 0) && e.globalEval(o)), o
            };
            return S
        }
        if (!this.length)
            return a("ajaxSubmit: skipping submit process - no element selected"), this;
        var u, l, c, f = this;
        "function" == typeof t && (t = {
            success: t
        }), u = t.type || this.attr2("method"), l = t.url || this.attr2("action"), c = "string" == typeof l ? e.trim(l) : "", c = c || window.location.href || "", c && (c = (c.match(/^([^#]+)/) || [])[1]), t = e.extend(!0, {
            url: c,
            success: e.ajaxSettings.success,
            type: u || "GET",
            iframeSrc: /^https/i.test(window.location.href || "") ? "javascript:false": "about:blank"
        }, t);
        var m = {};
        if (this.trigger("form-pre-serialize", [this, t, m]), m.veto)
            return a("ajaxSubmit: submit vetoed via form-pre-serialize trigger"), this;
        if (t.beforeSerialize && t.beforeSerialize(this, t)===!1)
            return a("ajaxSubmit: submit aborted via beforeSerialize callback"), this;
        var p = t.traditional;
        void 0 === p && (p = e.ajaxSettings.traditional);
        var d, h = [], v = this.formToArray(t.semantic, h);
        if (t.data && (t.extraData = t.data, d = e.param(t.data, p)), t.beforeSubmit && t.beforeSubmit(v, this, t)===!1)
            return a("ajaxSubmit: submit aborted via beforeSubmit callback"), this;
        if (this.trigger("form-submit-validate", [v, this, t, m]), m.veto)
            return a("ajaxSubmit: submit vetoed via form-submit-validate trigger"), this;
        var g = e.param(v, p);
        d && (g = g ? g + "&" + d : d), "GET" == t.type.toUpperCase() ? (t.url += (t.url.indexOf("?") >= 0 ? "&" : "?") + g, t.data = null) : t.data = g;
        var x = [];
        if (t.resetForm && x.push(function() {
            f.resetForm()
        }), t.clearForm && x.push(function() {
            f.clearForm(t.includeHidden)
        }), !t.dataType && t.target) {
            var b = t.success || function() {};
            x.push(function(r) {
                var a = t.replaceTarget ? "replaceWith": "html";
                e(t.target)[a](r).each(b, arguments)
            })
        } else 
            t.success && x.push(t.success);
        if (t.success = function(e, r, a) {
            for (var n = t.context || this, i = 0, o = x.length; o > i; i++)
                x[i].apply(n, [e, r, a || f, f])
        }, t.error) {
            var y = t.error;
            t.error = function(e, r, a) {
                var n = t.context || this;
                y.apply(n, [e, r, a, f])
            }
        }
        if (t.complete) {
            var T = t.complete;
            t.complete = function(e, r) {
                var a = t.context || this;
                T.apply(a, [e, r, f])
            }
        }
        var j = e('input[type=file]:enabled[value!=""]', this), w = j.length > 0, S = "multipart/form-data", k = f.attr("enctype") == S || f.attr("encoding") == S, D = n.fileapi && n.formdata;
        a("fileAPI :" + D);
        var A, E = (w || k)&&!D;
        t.iframe!==!1 && (t.iframe || E) ? t.closeKeepAlive ? e.get(t.closeKeepAlive, function() {
            A = s(v)
        }) : A = s(v) : A = (w || k) && D ? o(v) : e.ajax(t), f.removeData("jqxhr").data("jqxhr", A);
        for (var L = 0; h.length > L; L++)
            h[L] = null;
        return this.trigger("form-submit-notify", [this, t]), this
    }, e.fn.ajaxForm = function(n) {
        if (n = n || {}, n.delegation = n.delegation && e.isFunction(e.fn.on), !n.delegation && 0 === this.length) {
            var i = {
                s: this.selector,
                c: this.context
            };
            return !e.isReady && i.s ? (a("DOM not ready, queuing ajaxForm"), e(function() {
                e(i.s, i.c).ajaxForm(n)
            }), this) : (a("terminating; zero elements found by selector" + (e.isReady ? "" : " (DOM not ready)")), this)
        }
        return n.delegation ? (e(document).off("submit.form-plugin", this.selector, t).off("click.form-plugin", this.selector, r).on("submit.form-plugin", this.selector, n, t).on("click.form-plugin", this.selector, n, r), this) : this.ajaxFormUnbind().bind("submit.form-plugin", n, t).bind("click.form-plugin", n, r)
    }, e.fn.ajaxFormUnbind = function() {
        return this.unbind("submit.form-plugin click.form-plugin")
    }, e.fn.formToArray = function(t, r) {
        var a = [];
        if (0 === this.length)
            return a;
        var i = this[0], o = t ? i.getElementsByTagName("*"): i.elements;
        if (!o)
            return a;
        var s, u, l, c, f, m, p;
        for (s = 0, m = o.length; m > s; s++)
            if (f = o[s], l = f.name, l&&!f.disabled)
                if (t && i.clk && "image" == f.type)
                    i.clk == f && (a.push({
                        name: l,
                        value: e(f).val(),
                        type: f.type
                    }), a.push({
                        name: l + ".x",
                        value: i.clk_x
                    }, {
                        name: l + ".y",
                        value: i.clk_y
                    }));
                else if (c = e.fieldValue(f, !0), c && c.constructor == Array)
                    for (r && r.push(f), u = 0, p = c.length; p > u; u++)
                        a.push({
                            name: l,
                            value: c[u]
                        });
                else if (n.fileapi && "file" == f.type) {
                    r && r.push(f);
                    var d = f.files;
                    if (d.length)
                        for (u = 0; d.length > u; u++)
                            a.push({
                                name: l,
                                value: d[u],
                                type: f.type
                            });
                    else 
                        a.push({
                            name: l,
                            value: "",
                            type: f.type
                        })
                } else 
                    null !== c && void 0 !== c && (r && r.push(f), a.push({
            name: l,
            value: c,
            type: f.type,
            required: f.required
        }));
        if (!t && i.clk) {
            var h = e(i.clk), v = h[0];
            l = v.name, l&&!v.disabled && "image" == v.type && (a.push({
                name: l,
                value: h.val()
            }), a.push({
                name: l + ".x",
                value: i.clk_x
            }, {
                name: l + ".y",
                value: i.clk_y
            }))
        }
        return a
    }, e.fn.formSerialize = function(t) {
        return e.param(this.formToArray(t))
    }, e.fn.fieldSerialize = function(t) {
        var r = [];
        return this.each(function() {
            var a = this.name;
            if (a) {
                var n = e.fieldValue(this, t);
                if (n && n.constructor == Array)
                    for (var i = 0, o = n.length; o > i; i++)
                        r.push({
                            name: a,
                            value: n[i]
                        });
                else 
                    null !== n && void 0 !== n && r.push({
                    name: this.name,
                    value: n
                })
            }
        }), e.param(r)
    }, e.fn.fieldValue = function(t) {
        for (var r = [], a = 0, n = this.length; n > a; a++) {
            var i = this[a], o = e.fieldValue(i, t);
            null === o || void 0 === o || o.constructor == Array&&!o.length || (o.constructor == Array ? e.merge(r, o) : r.push(o))
        }
        return r
    }, e.fieldValue = function(t, r) {
        var a = t.name, n = t.type, i = t.tagName.toLowerCase();
        if (void 0 === r && (r=!0), r && (!a || t.disabled || "reset" == n || "button" == n || ("checkbox" == n || "radio" == n)&&!t.checked || ("submit" == n || "image" == n) && t.form && t.form.clk != t || "select" == i&&-1 == t.selectedIndex)
            )return null;
        if ("select" == i) {
            var o = t.selectedIndex;
            if (0 > o)
                return null;
            for (var s = [], u = t.options, l = "select-one" == n, c = l ? o + 1 : u.length, f = l ? o : 0; c > f; f++) {
                var m = u[f];
                if (m.selected) {
                    var p = m.value;
                    if (p || (p = m.attributes && m.attributes.value&&!m.attributes.value.specified ? m.text : m.value), l)
                        return p;
                    s.push(p)
                }
            }
            return s
        }
        return e(t).val()
    }, e.fn.clearForm = function(t) {
        return this.each(function() {
            e("input,select,textarea", this).clearFields(t)
        })
    }, e.fn.clearFields = e.fn.clearInputs = function(t) {
        var r = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;
        return this.each(function() {
            var a = this.type, n = this.tagName.toLowerCase();
            r.test(a) || "textarea" == n ? this.value = "" : "checkbox" == a || "radio" == a ? this.checked=!1 : "select" == n ? this.selectedIndex =- 1 : "file" == a ? /MSIE/.test(navigator.userAgent) ? e(this).replaceWith(e(this).clone(!0)) : e(this).val("") : t && (t===!0 && /hidden/.test(a) || "string" == typeof t && e(this).is(t)) && (this.value = "")
        })
    }, e.fn.resetForm = function() {
        return this.each(function() {
            ("function" == typeof this.reset || "object" == typeof this.reset&&!this.reset.nodeType) && this.reset()
        })
    }, e.fn.enable = function(e) {
        return void 0 === e && (e=!0), this.each(function() {
            this.disabled=!e
        })
    }, e.fn.selected = function(t) {
        return void 0 === t && (t=!0), this.each(function() {
            var r = this.type;
            if ("checkbox" == r || "radio" == r)
                this.checked = t;
            else if ("option" == this.tagName.toLowerCase()) {
                var a = e(this).parent("select");
                t && a[0] && "select-one" == a[0].type && a.find("option").selected(!1), this.selected = t
            }
        })
    }, e.fn.ajaxSubmit.debug=!1
}(jQuery);;
!function(e) {
    function t() {}
    function n(e) {
        i = [e]
    }
    function c(e, t, n) {
        return e && e.apply(t.context || t, n)
    }
    function r(e) {
        return /\?/.test(e) ? "&" : "?"
    }
    function o(o) {
        function m(e) {
            V++||(W(), K && (I[M] = {
                s: [e]
            }), A && (e = A.apply(o, [e])), c(R, o, [e, k, o]), c(z, o, [o, k]))
        }
        function S(e) {
            V++||(W(), K && e != x && (I[M] = e), c(U, o, [o, e]), c(z, o, [o, e]))
        }
        o = e.extend({}, B, o);
        var $, _, q, P, Q, R = o.success, U = o.error, z = o.complete, A = o.dataFilter, G = o.callbackParameter, H = o.callback, J = o.cache, K = o.pageCache, L = o.charset, M = o.url, N = o.data, O = o.timeout, V = 0, W = t;
        return w && w(function(e) {
            e.done(R).fail(U), R = e.resolve, U = e.reject
        }).promise(o), o.abort = function() {
            !V++&&W()
        }, c(o.beforeSend, o, [o])===!1 || V ? o : (M = M || l, N = N ? "string" == typeof N ? N : e.param(N, o.traditional) : l, M += N ? r(M) + N : l, G && (M += r(M) + encodeURIComponent(G) + "=?"), !J&&!K && (M += r(M) + "_" + (new Date).getTime() + "="), M = M.replace(/=\?(&|$)/, "=" + H + "$1"), K && ($ = I[M]) ? $.s ? m($.s[0]) : S($) : (C[H] = n, q = e(j)[0], q.id = f + T++, L && (q[u] = L), D && D.version() < 11.6 ? (P = e(j)[0]).text = "document.getElementById('" + q.id + "')." + h + "()" : q[a] = a, F && (q.htmlFor = q.id, q.event = p), q[y] = q[h] = q[v] = function(e) {
            if (!q[g] ||!/i/.test(q[g])) {
                try {
                    q[p] && q[p]()
                } catch (t) {}
                e = i, i = 0, e ? m(e[0]) : S(s)
            }
        }, q.src = M, W = function(e) {
            Q && clearTimeout(Q), q[v] = q[y] = q[h] = null, E[b](q), P && E[b](P)
        }, E[d](q, _ = E.firstChild), P && E[d](P, _), Q = O > 0 && setTimeout(function() {
            S(x)
        }, O)), o)
    }
    var i, a = "async", u = "charset", l = "", s = "error", d = "insertBefore", f = "_jqjsp", m = "on", p = m + "click", h = m + s, y = m + "load", v = m + "readystatechange", g = "readyState", b = "removeChild", j = "<script>", k = "success", x = "timeout", C = window, w = e.Deferred, E = e("head")[0] || document.documentElement, I = {}, T = 0, B = {
        callback: f,
        url: location.href
    }, D = C.opera, F=!!e("<div>").html("<!--[if IE]><i><![endif]-->").find("i").length;
    o.setup = function(t) {
        e.extend(B, t)
    }, e.jsonp = o
}(jQuery);;
$(function() {
    var pageTitle = "我在说蘑菇申请了免费试用“ " + document.title.replace("- 说蘑菇", "") + "”，试用通过率高，不用花钱，试用后也不用退还，还有更多流行热卖单品的免费试用活动，快来一起领取吧！(分享自 @说蘑菇 )", pageCont = $("meta[name=description]").attr("content"), pageImg = (location.href, $("#J_ImgUrl img").attr("src")), $bdshare = $("#bdshare"), str = '<a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a class="bds_count" data-cmd="count"></a>';
    with ($bdshare.html(str), window._bd_share_config = {
        common: {
            bdText: pageTitle,
            bdDesc: pageTitle,
            bdComment: pageCont,
            bdPic: pageImg,
            bdSign: "on"
        },
        share: {}
    }, document)
        0[(getElementsByTagName("head")[0] || body).appendChild(createElement("script")).src = "http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion="+~( - new Date / 36e5)]
});;
!function(t, i) {
    var e = {
        init: function() {
            t("#verify-ico").length <= 0 && t("#footer_slide_wrap .mod-wrap").append('<style>#verify-ico{float: left;width: 100%;}</style><div id="verify-ico" class="verify-ico"></div>')
        },
        verify: function() {
            e.init(), t("#verify-ico").append('<span id="kx_verify"></span>');
            var c = i.createElement("script");
            c.id = "kx_script", c.async=!0, c.setAttribute("cid", "kx_verify"), c.src = ("https:" == i.location.protocol ? "https://ss.knet.cn" : "http://rr.knet.cn") + "/static/js/icon3.js?sn=e14080145010052032idoy000000&tp=icon3", c.setAttribute("size", 0);
            var o = i.getElementById("kx_verify");
            o.parentNode.insertBefore(c, o)
        },
        cop: function() {
            e.init();
            var i = window.location.href.replace().split("/")[2].split(".")[1];
            t("#verify-ico").append('<a class="bigx" style="background: url(http://static.' + i + '.com/common/img/bigx.gif) no-repeat;display: inline-block;height: 44px;width: 45px;" href="http://www.gx.cyberpolice.cn/NewsCategory/lstNewCate.do" rel="nofollow"></a>')
        },
        fastcache: function() {
            var i = window.location.href.split("/")[2].split(".")[1];
            - 1 != window.location.href.indexOf("shikee") && t("#verify-ico").append('<a href="http://www.fastcache.com.cn/" style="background: url(http://static.' + i + '.com/common/img/fastcache_cdn_icon.jpg) no-repeat;display: inline-block;height: 45px;width: 181px;border-radius: 3px;border: 1px solid #ccc;margin-left:10px;" rel="nofollow"></a>')
        },
        baidu: function() {
            var t = "https:" == document.location.protocol ? " https:": " http:", e = i.createElement("script");
            e.src = t + "//hm.baidu.com/hm.js?dbe83be7b22900f25dc9ddb78881b58c";
            var c = i.getElementsByTagName("script")[0];
            c.parentNode.insertBefore(e, c)
        },
        cnzz: function() {
            var t = "https:" == document.location.protocol ? " https://": " http://";
            i.write(unescape("%3Cspan  style='display:none' id='cnzz_stat_icon_905537'%3E%3C/span%3E%3Cscript src='" + t + "s4.cnzz.com/stat.php%3Fid%3D905537' type='text/javascript'%3E%3C/script%3E"))
        }
    };
    t.statistical = function(t) {
        for (var i = t.split(","), c = 0; c < i.length; c++)
            "function" == typeof e[i[c]] && e[i[c]]()
    }
}(jQuery, document);;
!function(e, a, t) {
    function l(e) {
        var a = {}, l = /^jQuery\d+$/;
        return t.each(e.attributes, function(e, t) {
            t.specified&&!l.test(t.name) && (a[t.name] = t.value)
        }), a
    }
    function r(e, a) {
        var l = this, r = t(l);
        if (l.value == r.attr("placeholder") && r.hasClass("placeholder"))
            if (r.data("placeholder-password")) {
                if (r = r.hide().next().show().attr("id", r.removeAttr("id").data("placeholder-id")), e===!0)
                    return r[0].value = a;
                    r.focus()
            } else 
                l.value = "", r.removeClass("placeholder"), l == d() && l.select()
    }
    function o() {
        var e, a = this, o = t(a), d = this.id;
        if ("" == a.value) {
            if ("password" == a.type) {
                if (!o.data("placeholder-textinput")) {
                    try {
                        e = o.clone().attr({
                            type: "text"
                        })
                    } catch (c) {
                        e = t("<input>").attr(t.extend(l(this), {
                            type: "text"
                        }))
                    }
                    e.removeAttr("name").data({
                        "placeholder-password": o,
                        "placeholder-id": d
                    }).bind("focus.placeholder", r), o.data({
                        "placeholder-textinput": e,
                        "placeholder-id": d
                    }).before(e)
                }
                o = o.removeAttr("id").hide().prev().attr("id", d).show()
            }
            o.addClass("placeholder"), o[0].value = o.attr("placeholder")
        } else 
            o.removeClass("placeholder")
    }
    function d() {
        try {
            return a.activeElement
        } catch (e) {}
    }
    var c, n, i = "placeholder"in a.createElement("input"), u = "placeholder"in a.createElement("textarea"), h = t.fn, p = t.valHooks, s = t.propHooks;
    i && u ? (n = h.placeholder = function() {
        return this
    }, n.input = n.textarea=!0) : (n = h.placeholder = function() {
        var e = this;
        return e.filter((i ? "textarea" : ":input") + "[placeholder]").not(".placeholder").bind({
            "focus.placeholder": r,
            "blur.placeholder": o
        }).data("placeholder-enabled", !0).trigger("blur.placeholder"), e
    }, n.input = i, n.textarea = u, c = {
        get: function(e) {
            var a = t(e), l = a.data("placeholder-password");
            return l ? l[0].value : a.data("placeholder-enabled") && a.hasClass("placeholder") ? "" : e.value
        },
        set: function(e, a) {
            var l = t(e), c = l.data("placeholder-password");
            return c ? c[0].value = a : l.data("placeholder-enabled") ? ("" == a ? (e.value = a, e != d() && o.call(e)) : l.hasClass("placeholder") ? r.call(e, !0, a) || (e.value = a) : e.value = a, l) : e.value = a
        }
    }, i || (p.input = c, s.value = c), u || (p.textarea = c, s.value = c), t(function() {
        t(a).delegate("form", "submit.placeholder", function() {
            var e = t(".placeholder", this).each(r);
            setTimeout(function() {
                e.each(o)
            }, 10)
        })
    }), t(e).bind("beforeunload.placeholder", function() {
        t(".placeholder").each(function() {
            this.value = ""
        })
    }))
}(this, document, jQuery);;
var Pass = function() {
    var t = $("#passBtn");
    return {
        init: function() {
            try_data.is_join_use_pass && t.attr("href", "javascript:void(0);").html("已兑换").removeClass("passBtn").addClass("tryEndBtn")
        },
        check: function() {
            if (!Detail.base_check())
                return !1;
            if (t.hasClass("tryEndBtn"))
                return !1;
            var a = "<dl><p>确定用<span style='color:#F99A20; font-weight:700;'>" + 50 * Math.ceil(try_data.price / 50) + '</span>元试用通过券兑换这款试用品吗？</p><div style="margin-top:30px; text-align:center;overflow:hidden; "><a href="javascript:;" class="closeWin" onclick="Pass.go(true)" >确定</a>&nbsp;<a href="javascript:;" class="closeWin" onclick=\'closeArt()\'>返回</a></div>';
            Kee.use("artDialog", function() {
                art.dialog({
                    id: "win_pass_ct",
                    lock: !0,
                    fixed: !0,
                    opacity: .6,
                    drag: !1,
                    title: "<strong>温馨提示:</strong>",
                    content: a
                })
            })
        },
        go: function(a) {
            (a || this.check()) && (t.attr("href", "javascript:void(0);"), Kee.use("artDialog", function() {
                art.dialog({
                    id: "win_pass_ct"
                }).close()
            }), $.ajax({
                url: "/use_pass/" + try_data.tid,
                dataType: "jsonp",
                type: "POST",
                data: {
                    cache_key: try_data.key
                },
                success: function(a) {
                    return t.attr("href", "javascript:Pass.go(true);"), a.success ? void Pass.success(a) : void Ui.error(a.info)
                },
                error: function(a, s) {
                    t.attr("href", "javascript:Pass.go(true);"), Ui.error("timeout" == s ? "系统连接超时，请稍后重试。" : "系统繁忙，请稍后重试！")
                }
            }, "json"))
        },
        success: function(a) {
            t.attr("href", "javascript:void(0);").html("已兑换").removeClass("passBtn").addClass("tryEndBtn");
            var s = "<div> <strong style=\"color:#E36C04;\">兑换成功!</strong><p style='text-indent: 2em; line-height:24px; color:#666; padding:10px 0'>恭喜您获得了活动" + try_data.title + "的试用资格，请在" + a.join_wait_day_no + "天内到商家店铺下单购买并返回个人中心填写订单号，逾期您的本次试用资格将被取消！</p><a style=' color:#295394' href=\"" + sk.site("user") + "\">到个人中心看看 >></a></div><p class='close-p'><a class='closeWin' href='javascript:closeArt();'>我知道了</a></p>";
            Kee.use("artDialog", function() {
                art.dialog({
                    id: "win-pass-ok",
                    lock: !0,
                    fixed: !0,
                    opacity: .6,
                    title: "<strong>温馨提示:</strong>",
                    content: s,
                    width: "400px",
                    drag: !1
                })
            })
        }
    }
}();;
var Report = function() {
    function t(t) {
        if (html = [], html.push('<ul id="reportlist" class="reportlist">'), t.total > 0) {
            for (i in t.list) {
                var e = t.list[i];
                html.push('<li class="report-item">'), html.push('<a class="pic" href="', e.user_space, '"><img src="', e.avatar, '" width="70" height="70" onerror="this.onerror=null;this.src=\'', a, "'\" ><span>", e.buyer_uname, "</span></a>"), html.push('<div class="info">'), html.push("<h4><span>发表于", e.dateline, '</span><a href="', r, "detail/", e.jid, '.html">', e.title, "</a></h4>"), 3 == e.quantity ? html.push('<strong class="jinghua"></strong>') : 2 == e.quantity && html.push('<strong class="unquali"></strong>'), html.push('<ul class="mt10 reportimg clearfix">');
                for (var h in e.smallimg)
                    html.push('<li><a target="_blank" href="', r, "detail/", e.jid, '.html" target="_blank"><img width="60" height="60"  src="', e.smallimg[h], '" onerror="this.onerror=null;this.src=\'', s, "'\" ></a></li>");
                html.push("</ul>"), html.push("</div></li>")
            }
            html.push("</ul>"), t.page && html.push('<div class="pageCenter" id="J-page-sybg">', t.page, "</div>")
        } else 
            html.push('<li><p class="no-data fc-red">还没有用户提交报告哦！赶快去申请试用写报告吧！</p></li>');
        l.html(html.join(""))
    }
    var l = $("#J_TryReport"), s = GV.CDN_ROOT + "common/img/default_trys_img/75.png", a = GV.CDN_ROOT + "common/img/default_avatar/noavatar_middle.gif", r = (sk.site("bbs"), sk.site("report")), e=!1, h=!1;
    return {
        load: function(l) {
            "undefined" == typeof l && h || e || (l = l > 0 ? l : 1, e=!0, $.get("/detail/report/" + try_data.tid + "/" + l, function(l) {
                t(l.data), h=!0, e=!1
            }, "json"))
        }
    }
}();;
function show_left_time(t) {
    if (0 >= t)
        return $("#showclock").html("<b>已结束</b>"), try_data.state = try_state.offline, void update_apply_btn();
    var a, e = t - 1, i = ((new Date).getTime(), 0), s = 0, r = 0, n = 0, o = "", l = "", c = "", _ = "";
    i = parseInt(t / 86400), t -= 86400 * i, s = parseInt(t / 3600), t -= 3600 * s, r = parseInt(t / 60), n = t - 60 * r, o = i, l = s, c = r, _ = n, 10 > s && (l = "0" + s), 10 > r && (c = "0" + r), 10 > n && (_ = "0" + n), o = i > 0 ? "<em>" + o + " </em> 天 " : "", l = s > 0 ? "<em>" + l + "</em> 小时 " : "" != o ? "<em>00</em> 小时 " : "", c = r > 0 ? "<em>" + c + "</em> 分钟 " : "" != o || "" != l ? "<em>00</em> 分钟 " : "", _ = n > 0 ? "<em>" + _ + "</em> 秒 " : "" != o || "" != l || "" != c ? "<em>00</em> 秒 " : "", a = o + l + c + _, document.getElementById("showclock").innerHTML = a, setTimeout("show_left_time( " + e + ") ", 1e3)
}
function update_apply_btn() {
    var t = $("#showclock b").html();
    "已结束" == t && ($("#J_btn").attr("href", "javascript:void(0);").html("已结束").removeClass("joinBtn").addClass("tryEndBtn"), $("#passBtn").attr("href", "javascript:void(0);").removeClass("passBtn").addClass("tryEndBtn"))
}
var Ui = function() {
    function t(t, a) {
        $("#" + t).html(a).removeClass("wait-data")
    }
    return $("#tryJoinFlow").find("li").hover(function() {
        $(this).find(".hintDiv").show()
    }, function() {
        $(this).find(".hintDiv").hide()
    }), $(".tryOrderTip").hover(function() {
        $(this).find(".tryOrderTip-cont").show()
    }, function() {
        $(this).find(".tryOrderTip-cont").hide()
    }), $("body").delegate(".notclick", "click", function() {
        return !1
    }), $("#smg_answer").on("focus", function() {
        $(this).removeClass("input_error"), $("#skanswerTip").remove()
    }), {
        init: function() {
            var a = (try_dynamic || {}, try_data || {}, try_data.total_being_join), e = 0 >= a ? 0: a;
            return t("try_total_apply", try_dynamic.total_apply), t("try_hits", try_dynamic.hits), t("try_total_join", Math.max(0, try_dynamic.total_passed - try_dynamic.total_success - try_dynamic.total_finish_failure)), t("try_total_finish_success", e), t("try_quantity_remain", try_dynamic.quantity_remain), t("try_pass_remain", 0 == try_data.pass_limit_time || try_data.pass_limit_time > try_data.now ? try_data.pass_remain : 0), $("#tab_report a").html("试用报告（" + try_data.total_report_check_success + "）"), $("#tab_user a").html(4 == try_data ? "已下单购买（" + try_data.total_confirm + "）" : "已下单名单（" + e + "）"), $("#tabs_main > li ").click(function() {
                var t = $(this).index();
                $("#tabs_main > li ").removeClass("sel").eq(t).addClass("sel"), $("#tabs_content > div").hide().eq(t).show(), 1 == t ? Report.load() : 2 == t && User.load()
            }), 1 == try_data.is_block ? ($("#showclock").html("<b>屏蔽中</b>"), !1) : (try_data.state < 3 ? $("#showclock").html("<b>尚未上线</b>") : show_left_time(try_data.end_time - try_data.now), void update_apply_btn())
        },
        show_qr: function() {
            $("#tabs_main > li ").eq(0).click(), $("html,body").animate({
                scrollTop: $("#tabs_content").offset().top
            }, 500)
        },
        error: function(t) {
            var a;
            "string" == typeof t ? a = t : "object" == typeof t && (a = t.reason ? "<h1 class='error-title'>" + t.data + "</h1><div class='cause'>原因:" + t.reason + "</div><p class='close-p'><a class='closeWin' href='javascript:closeArt();'>我知道了</a></p>" : "<h1 class='error-title'>" + t.data + "</h1><p class='close-p'><a class='closeWin' href='javascript:closeArt();'>我知道了</a></p>"), Kee.use("artDialog", function() {
                art.dialog({
                    lock: !0,
                    fixed: !0,
                    title: "温馨提示",
                    drag: !1,
                    content: a
                })
            })
        },
        win_close: function(t) {
            Kee.use("artDialog", function() {
                art.dialog({
                    id: t
                }).close()
            })
        }
    }
}();;
var User = function() {
    var a = $("#passUser"), t = (GV.CDN_ROOT + "common/img/default_trys_img/75.png", GV.CDN_ROOT + "common/img/default_avatar/noavatar_middle.gif"), o = (sk.site("bbs"), sk.site("report"), !1), s=!1, e = null, l = 0;
    return {
        load_more: function() {
            this.load(e + 1)
        },
        load: function(n) {
            "undefined" == typeof n && s || o || (o=!0, n = n > 0 ? n : 1, e = n, $.post("/detail/passed/" + try_data.tid + "/" + n, function(e) {
                if (e = e.data, !(e.alltotal <= l)) {
                    {
                        $("#passUser")
                    }
                    if (html = [], e.total > 0) {
                        for (i in e.list)
                            vo = e.list[i], html.push('<a href="' + vo.user_space + '">'), html.push("<img onerror=\"this.onerror=null;this.src='", t, "'\" alt=" + vo.uname + ' src="' + vo.avatar + '" /> <span>' + vo.buyer_uname + "</span> </a>"), l++;
                        $("#new_offset").val(e.new_offset)
                    } else 
                        html.push('<p class="no-data fc-red">还没有用户申请，赶快来抢购吧！</p>');
                    e.alltotal > 15 * n && $("#morelink").show(), a.append(html.join("")), e.alltotal <= l && $("#morelink").remove(), s=!0, o=!1
                }
            }, "json"))
        }
    }
}();;
!function(t, a) {
    var e, i = 0, s = t("#J_btn"), r = function(a) {
        t.ajax({
            url: "/detail/flash_state/" + a + "?_=" + (new Date).getTime(),
            type: "GET",
            dataType: "json",
            success: function(e) {
                if (e.success)
                    switch (e.data.state) {
                    case 1:
                        Ui.error("闪电抢购已结束。");
                        break;
                    case 3:
                        if (i++, 10 == i)
                            return i = 0, Ui.error("服务繁忙"), !1;
                            setTimeout(function() {
                                r(a)
                            }, 1e3);
                            break;
                        case 4:
                            var n = function(a, e) {
                                var i = t.extend({
                                    id: "artDialogId",
                                    title: "",
                                    lock: !0,
                                    fixed: !0,
                                    opacity: .6,
                                    drag: !1,
                                    content: "",
                                    width: "",
                                    init: function() {
                                        "undefined" != typeof e && e()
                                    }
                                }, a);
                                Kee.use("artDialog", function() {
                                    art.dialog(i)
                                })
                            }, o = function(a) {
                                var e = t("#J_orderUpdate"), i = /^[a-zA-Z0-9-]{5,50}$/, s = t("#msgbox");
                                return e.addClass("btn-disable").prop("disabled", !0), "" == a ? void s.html("订单号不能为空") : a.length < 5 || a.length > 50 ? void s.html("订单号长度必须在5到50位之间。") : i.test(a) ? (s.html(""), void e.removeClass("btn-disable").prop("disabled", !1)) : void s.html("订单号只能为数字、字母或两者组合！")
                            }, l = window.location.href.split(".")[1], c = '<div class="snap-up-success"><p class="success-tip">恭喜您获得本次试用资格！请按页面流程前往商品所在下单，并于45分钟内返回说蘑菇网站填写订单号，逾期将视为自动放弃试用资格。</p><p class="success-operate"><a href="javascript:;" class="operate-btn" id="J_goOrder">前往下单</a><a href="http://list.' + l + '.com/list-1.html" class="operate-btn"  target="_self">继续抢试用</a></p></div>', d = s.data("tryimg"), p = s.data("trytitle"), u = '<div class="ui-popmsg"><div class="ui-popmsg-txt-col">提示：填错订单号您将无法领取试用担保金,请您务必正确填写！</div><div class="ui-popmsg-cont clearfix"><img src="' + d + '" width="60" height="60"   /><h2>' + p + '</h2></div><div class="ui-form"><span>请粘贴交易订单号：</span><input id="orderNum" name="tradeorderno" type="text" size="20px" class="input-gray" />       <span id="msgbox" class="font-red"></span></div><a href="http://help.' + l + '.com/shike?id=4823&cid=126" class="ui-popmsg-links" target="_blank"> > 如何填写订单号</a><input type="button" class="btn btn-disable" value="确定提交" id="J_orderUpdate" /></div>', f = t("#J_price").val(), g = e.data.time_wait_no, v = t("#J_url").val(), h = '<div class="ui-popmsg"><div class="order-tip"><div class="order-tip-explain"><p class="font-red">该活动为二维码下单活动，请扫描右边的二维码进入商家店铺下单购买。（请勿用微信扫码）</p><p>扫描二维码后将跳转到商家指定平台店铺， 下单购买前请<span class="font-red">确认下单价为' + f + '元。</span>请<span class="font-red">在' + g + '前下单并返回粘贴订单号</span>，逾期系统将取消您本次试用资格。</p></div><div class="order-tip-img"><img class="" src="' + v + '"></div></div></div>';
                            n({
                                title: "抢试用成功",
                                content: c,
                                width: 500
                            }, function() {
                                t("#J_goOrder").on("click", function() {
                                    if (0 == try_data.is_qr_order) {
                                        var a = t("#J_ImgUrl").find(".links").attr("href");
                                        "" != a && void 0 != a && window.open(a), closeArt(), n({
                                            title: "填写订单编号",
                                            content: u
                                        }, function() {
                                            var a = t("#J_orderUpdate"), i=!1, s = t("#orderNum");
                                            s.on("keyup", function() {
                                                var a = t.trim(t("#orderNum").val());
                                                o(a)
                                            }), a.on("click", function() {
                                                if (!i) {
                                                    i=!0;
                                                    var r = t.trim(t("#orderNum").val()), n = e.data.jid, o = try_data.tid;
                                                    if ("" == r)
                                                        return t("#msgbox").html("订单号不能为空"), void a.prop("disabled", !0);
                                                        a.val("提交中...").prop("disabled", !0).addClass("btn-disable"), s.prop("disabled", !0);
                                                        var c = "http://user." + l + ".com";
                                                        t.ajax({
                                                            url: "" + c + "/buyer/join/write_trade_no/",
                                                            data: {
                                                                tid: o,
                                                                jid: n,
                                                                tradeorderno: r
                                                            },
                                                            type: "get",
                                                            dataType: "jsonp",
                                                            success: function(e) {
                                                                console.log(e, 1), 1 == e.success ? (closeArt(), Kee.use("tipMes", function() {
                                                                    t.msgbox.show({
                                                                        message: "填写订单成功！",
                                                                        icon: "ok",
                                                                        timeOut: 1e3
                                                                    })
                                                                }), window.setTimeout(function() {
                                                                    location.reload()
                                                                }, 1e3)) : (t("#msgbox").html(e.data), s.prop("disabled", !1), a.addClass("btn-disable").val("确定提交").prop("disabled", !1))
                                                            },
                                                            complete: function() {
                                                                i=!1
                                                            },
                                                            error: function() {}
                                                        })
                                                }
                                            })
                                        })
                                    } else 
                                        closeArt(), n({
                                            title: "下单前提示",
                                            width: 600,
                                            content: h
                                        })
                                    })
                                }), s.text("抢试用成功"), s.addClass("btn-disable");
                                var b = t("#try_total_apply"), m = t("#try_quantity_remain"), _=~~b.text(), k=~~m.text();
                                b.text(++_), m.text(--k);
                                break;
                            case 5:
                                Ui.error("获得试用资格失败"), s.removeClass("btn-disable"), s.text("马上试用");
                                break;
                            case 6:
                                Ui.error("还有机会");
                                break;
                            default:
                                Ui.error("未知状态值" + e.data.state)
                    } else 
                        Ui.error(e.info || e.data.state)
            },
            error: function(t) {
                Ui.error("timeout" == t ? "系统连接超时，请稍后重试。" : "系统繁忙，请稍后重试！")
            }
        })
    }, n = function() {
        t.ajax({
            url: "/detail/apply/" + try_data.tid,
            type: "POST",
            data: {
                cache_key: try_data.key
            },
            dataType: "json",
            success: function(t) {
                t.success ? (s.text("排队中..."), s.addClass("btn-disable"), r(try_data.tid)) : (Ui.error(t.info), "undefined" == typeof t.info.state || 2 != t.info.state && 3 != t.info.state || (s.text("还有机会"), s.addClass("btn-disable")))
            },
            error: function(t) {
                Ui.error("timeout" == t ? "系统连接超时，请稍后重试。" : "系统繁忙，请稍后重试！")
            }
        })
    };
    a.checkPublic = {
        base_check: function() {
            var t = sk.user.info();
            return t ? 2 == t.type ? (Ui.error('<p class="error-cont">商家账号不可参与试用！</p><div class="long-box"><a href="' + sk.site("login") + "login/logout?to=" + sk.site("login") + '" class="longBtn-a">试客登录</a><a href="' + sk.site("reg") + '" class="longBtn-a">试客注册</a></div>'), !1) : 1 != t.type ? (Ui.error('<p class="error-cont">此账号类型不可参与试用！</p><div class="long-box"><a href="' + sk.site("login") + "login/logout?to=" + sk.site("login") + '" class="longBtn-a">试客登录</a><a href="' + sk.site("reg") + '" class="longBtn-a">试客注册</a></div>'), !1) : !0 : (sk.user.login(location.href), !1)
        },
        check: function() {
            var a = t.trim(try_data.url_favorites), e = t.trim(try_data.url_share), i = this;
            if (a && 2 != try_data.type) {
                var s = "<div class='lonbar'><p class='tishi_collect'>收藏宝贝就可以成功提交申请哦！</p><p class='go_bd'><a class='longBtn-a' href='" + a + "' target='_blank'>去收藏</a>&nbsp;&nbsp;<a class='longBtn-d notclick' id='linkCofirm''  href='javascript:void(0);'>确认申请</a></p></div>";
                return Kee.use("artDialog", function() {
                    art.dialog({
                        lock: !0,
                        fixed: !0,
                        opacity: .6,
                        drag: !1,
                        title: "<strong>温馨提示:</strong>",
                        content: s,
                        init: function() {
                            var a = t(this.DOM.content.find(".longBtn-a")[0]);
                            a.on("click", function() {
                                i.wait_to_apply(5)
                            })
                        }
                    })
                }), !1
            }
            if (e && 2 != try_data.type)
                return s = "<div class='lonbar'><p class='tishi_collect'>分享宝贝就可以成功提交申请哦！</p><p  class='go_bd'><a class='longBtn-a' href='" + e + "' target='_blank' onclick='sk.checkPublic.wait_to_apply(5)' target='_self'>去分享</a>&nbsp;&nbsp;<a class='longBtn-d notclick' id='linkCofirm'  href='javascript:void(0)'>确认申请</a></p></div>", Kee.use("artDialog", function() {
                    art.dialog({
                        id: "win-favorite",
                        lock: !0,
                        fixed: !0,
                        opacity: .6,
                        title: "<strong>温馨提示:</strong>",
                        content: s
                    })
                }), !1;
            if (4 == try_data.type) {
                var r = t("#smg_answer").val();
                return t("#smg_answer").parent("li").css("position", "relative").append('<div class="skanswerTip" id="skanswerTip">您的答案不正确哦！请再仔细找找或者　<a href="http://help.shikee.com/guize/sylc/shiyongliucheng/2014-09-30/640.html" target="_blank"><u>查看找答案攻略</u></a></div>'), t.post("/detail/check_answer/" + try_data.tid + "/", {
                    answer: r
                }, function(a) {
                    a.success ? n(!0) : (t("#smg_answer").addClass("input_error"), t("#skanswerTip").show())
                }, "json"), !1
            }
            n(!0)
        },
        wait_to_apply: function(a) {
            if (a > 0)
                if (t("#linkCofirm").hasClass("longBtn-a"))
                    clearTimeout(e);
                else {
                    t("#linkCofirm").html("请等待 <b>" + a + "</b> 秒");
                    var i = this;
                    e = setTimeout(function() {
                        i.wait_to_apply(a - 1)
                    }, 1e3)
                } else 
                    t("#linkCofirm").html("确认申请").attr({
                        "class": "longBtn-a",
                        href: "javascript:void(0);"
                    }).click(function() {
                        t(this).unbind("click"), closeArt(), n(!0)
                    })
        }
    }
}(jQuery, window.sk || {}), !function() {
    $("#J_btn")
}();;
var try_state = {
    uncheck: 1,
    checked: 2,
    online: 3,
    offline: 4,
    checkouted: 5,
    deleted: 6
};
$.extend(try_data, try_dynamic), !function(t) {
    "use strict";
    var e = t("#J_btn"), a = GV.try_flash_info || {}, i = a.length, s = "", n = t("#J_innoclockDc"), c = {
        init: function() {
            c.view.timerShaft(), c.view.btnState(), c.view.timingInquire(c.view.getNowTime(try_data.now)), Ui.init()
        }
    };
    c.view = {
        timerShaft: function() {
            var e = this.getNowTime(try_data.now), s = [], n = t("#J_timeShaft"), c = n.parent(".time-shaft-wrap"), r = function(t) {
                var e = parseInt(t / 3600), a = parseInt((t - 3600 * e) / 60), i = 10 > e ? "0" + e: e, s = 10 > a ? "0" + a: a;
                return i + ":" + s
            }, l = function(t) {
                var e = 100 / i + "%";
                t.css("width", e)
            }, o = function() {
                return t.each(a, function(t, a) {
                    var i = a.end_time, n = a.start_time, c = "", l = "", o = "";
                    if (e > n && e > i)
                        c = "已结束", o = "act-end-bg";
                    else if (i >= e && e >= n) {
                        c = "进行中", l = "font-big";
                        var d = try_dynamic.try_flash_quantity.remain;
                        o = d > 0 ? "act-on-bg-platinum" : "act-end-bg"
                    } else 
                        n > e && i > e && (c = "未开始", o = "act-NotStarted-bg");
                    (4 == try_data.state || 5 == try_data.state) && (c = "已结束", o = "act-end-bg"), 1 == try_data.is_block && (o = "act-end-bg"), s.push('<div class="time-shaft-time ' + o + '">'), s.push('    <div class="status-line"></div>'), s.push('<span class="time-shaft-clock" ></span>'), s.push('    <div class="time-shaft-info ' + l + '">'), s.push("        <p>" + a.quantity + "份</p>"), s.push('        <p class="time-quantum">' + r(a.start_time) + "-" + r(a.end_time) + "</p>"), s.push("    </div>"), s.push('    <p class="actStatus-txt">' + c + "</p>"), s.push("</div>")
                }), n.append(s.join(""))
            };
            if (o(), 1 == try_data.is_block) {
                var d = t(".actStatus-txt");
                d.addClass("actBlocked")
            }
            l(t(".time-shaft-time")), 0 != a.length ? c.css("display", "block") : c.css("display", "none")
        },
        btnState: function() {
            var c = t(".joinBtn");
            switch (c.css("display", "inline-block"), ~~try_data.state) {
            case 1:
                e.text("审核中"), e.addClass("btn-disable");
                break;
            case 2:
                e.text("已审核待上线"), e.addClass("btn-disable");
                break;
            case 3:
                var r = try_dynamic.try_flash_quantity.remain, l = t("#J_buy-time-tip");
                if (1 == try_dynamic.is_period) {
                    var o = this.getNowTime(try_data.now), d = 0, _ = 0;
                    l.text("离下次开抢时间还剩下:"), t(".buy-time-remaining").css("display", "block");
                    for (var m = 0; i > m; m++) {
                        var b = a[m].start_time, u = a[m].end_time, h = a[i - 1].end_time;
                        if (b > o) {
                            e.text("敬请期待"), e.addClass("btn-disable");
                            break
                        }
                        if (o >= b && u >= o) {
                            l.text("本时段活动剩余时间:"), r > 0 ? (e.text("我要抢试用"), e.removeClass("btn-disable")) : (e.text("还有机会"), e.addClass("btn-disable"));
                            break
                        }
                        if (o > h) {
                            e.text("还有机会"), e.addClass("btn-disable");
                            break
                        }
                        if (o > b && o > u && o < a[m + 1].start_time) {
                            e.text("敬请期待"), e.addClass("btn-disable");
                            break
                        }
                    }
                    s = b, _ = u, d = m == i - 1 ? "" : a[m + 1].start_time, o < a[0].start_time ? this.countDown(1e3 * (a[0].start_time - this.getNowTime(try_data.now))) : h >= o && b != a[i - 1].start_time && 1 != try_data.is_block ? this.countDown(o > a[m].start_time && o < a[m].end_time ? 1e3 * (_ - this.getNowTime(try_data.now)) : 1e3 * (d - this.getNowTime(try_data.now))) : o >= a[i - 1].start_time && this.countDown(o > a[m].start_time && o < a[m].end_time ? 1e3 * (_ - this.getNowTime(try_data.now)) : 1e3 * (86400-~~this.getNowTime(try_data.now)+~~a[0].start_time))
                } else (4 != try_data.state || 5 != try_data.state) 
                    && (r > 0 ? (e.text("我要抢试用"), e.removeClass("btn-disable")) : (e.text("还有机会"), e.addClass("btn-disable")));
                try_dynamic.is_apply && try_dynamic.is_join && (e.text("抢试用成功"), e.addClass("btn-disable")), 1 == try_data.is_block && (e.text("屏蔽中"), e.addClass("btn-disable"), n.find(".clock").html('<i class="clock-h">0</i>时<i class="clock-m">0</i>分<i class="clock-s">0</i>秒'));
                break;
            case 4:
                e.text("已结束"), e.addClass("btn-disable");
                break;
            case 5:
                e.text("活动结束"), e.addClass("btn-disable"), n.find(".clock").html('<i class="clock-h">0</i>时<i class="clock-m">0</i>分<i class="clock-s">0</i>秒')
            }
        },
        getNowTime: function(t) {
            if (0 >= t)
                return 0;
            var e = new Date;
            e.setTime(1e3 * t);
            var a = e.getHours(), i = e.getMinutes(), s = e.getSeconds();
            return 3600 * a + 60 * i + s
        },
        timingInquire: function(t) {
            var a = null;
            a = setInterval(function() {
                t += 1, t == s && (clearInterval(a), e.text("马上抢购"), e.removeClass("btn-disable"))
            }, 1e3)
        },
        countDown: function(t) {
            setInterval(function() {
                var e = n.find(".clock-h"), a = n.find(".clock-m"), i = n.find(".clock-s");
                t -= 1e3;
                var s = Math.floor(t / 36e5)%24, c = Math.floor(t / 6e4)%60, r = Math.floor(t / 1e3)%60;
                (0 > s || 0 > c || 0 > r) && location.reload(), e.text(s), a.text(c), i.text(r)
            }, 1e3)
        }
    }, c.operation = {
        apply: function() {
            var t = sk.checkPublic.base_check();
            t && sk.checkPublic.check()
        }
    }, c.init(), e.on("click", function() {
        return t(this).hasClass("btn-disable")?!1 : void c.operation.apply()
    })
}(jQuery), $("#f_con").Xslider({
    effect: "scrollx",
    auto: !0,
    content_box: "#f_list",
    index: "list",
    load_type: "img",
    space: 2e3,
    prev: ".focus-left",
    next: ".focus-right",
    isCheckShow: !1
}), $("#keySearch").placeholder();;
!function(o) {
    var t = o("body"), i = {
        init: function() {
            var i = window.location.href.split(".")[1], e = o('<div id="fixedObj" style="position:fixed;right:20px;bottom:30px;_position:absolute;width:48px;z-index:99999;display: none;"><a href="#" title="回到顶部" target="_self" id="backToTop" style="display:block;width:48px;height:48px;background:url(http://static.' + i + '.com/common/img/back_top.png) no-repeat center #f9475c;text-indent:-99999px;"></a></div>');
            t.append(e)
        },
        throttle: function(o) {
            clearTimeout(o.tId), o.tId = setTimeout(function() {
                o.call()
            }, 500)
        },
        gotoOperation: function() {
            var t=!-[1]&&!window.XMLHttpRequest, i = o(this).scrollTop(), e = o(window).height();
            t ? i > e ? o("#fixedObj").show().stop().animate({
                top: e - 60 + i
            }, 10) : o("#fixedObj").hide() : i > e ? o("#fixedObj").fadeIn() : o("#fixedObj").fadeOut()
        }
    };
    o(window).scroll(function() {
        i.throttle(i.gotoOperation)
    }), t.on("mouseover", "#backToTop", function() {
        o(this).css("background-color", "#d73547")
    }).on("mouseleave", "#backToTop", function() {
        o(this).css("background-color", "#f9475c")
    }), i.init()
}(jQuery);

