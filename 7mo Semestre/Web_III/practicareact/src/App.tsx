import './App.css'

interface AppProps {
  title: string
}

function App({title}: AppProps) {

  return (
    <>
      {title}
    </>
  )
}

export default App
