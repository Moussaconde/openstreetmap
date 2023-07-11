function raw_tex (t)
  return pandoc.RawBlock('tex', t)
end

function CodeBlock (cb)
  return {
    raw_tex'\\begin{tcolorbox}', cb, raw_tex '\\end{tcolorbox}'
  }
end

function Meta (m)
  m['header-includes'] = m['header-includes'].. {
    raw_tex ('\\usepackage{tcolorbox}'..
      '\\tcbset{colback=blueGrey!100!jawgMaps,size=small}'..
      '\\tcbsetforeverylayer{colframe=jawgMaps!100!black}')
  }
  return m
end