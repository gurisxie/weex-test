# &lt;web&gt;

### Summary

Use web component to display any web content in the weex page. The `src` attribute is used to specify a special source. You also can use `webview` module to control some web operation such as goBack、goForward and reload, see [webview module](../modules/webview.md).


### Child Components

This component supports no child components.

### Attributes

- `src`: &lt;string&gt; this attribute specifies the page source to load.

Other attributes please check out the [common attributes](../references/common-attrs.md).

### Styles

- `width`: &lt;length&gt; the width of the component. This style should be specified.
- `height`: &lt;length&gt; the height of the component. This style should be specifed.

**common styles**: check out the [common styles](../references/common-attrs.md)

- support flexbox related styles
- support box model related styles
- support ``position`` related styles

### Events

- `pagestart`: sent after the web component starts loading a page. 
- `pagefinish`: sent after the web component finishes loading a page.
- `error`: sent if the web component failed to load a page. 


*common events**: check out the [common events](../references/common-event.md)

- support `click` event. Check out [common events](../references/common-event.md)
- support `appear` / `disappear` event. Check out [common events](../references/common-event.md)

### Example

```js
<div>
  <web style="width=...; height=...;" src="..." 	onpagestart="pagestart" onpagefinish="pagefinish" 	onerror="error">
  </web>
</div>
```