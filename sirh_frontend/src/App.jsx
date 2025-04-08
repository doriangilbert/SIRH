import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios'

function App() {
    const [data, setData] = useState([])
    const [columns, setColumns] = useState([])
    const [error, setError] = useState(null)
    const [selectedClass, setSelectedClass] = useState('')
    const [relatedDetails, setRelatedDetails] = useState(null)
    const [showRelatedDetails, setShowRelatedDetails] = useState(false)

    const formatRouteName = (routeName) => {
        const lowerRouteName = routeName.toLowerCase()
        if (["manager", "reviewer"].includes(lowerRouteName)) {
            return "employees"
        }
        if (!lowerRouteName.endsWith('s')) {
            return lowerRouteName + 's'
        }
        return lowerRouteName
    }

    const fetchData = (selectedClass) => {
        setSelectedClass(selectedClass)
        setShowRelatedDetails(false)
        axios.get(`http://localhost:8080/${selectedClass}`)
            .then(response => {
                console.log(response.data)
                const sortedData = Array.isArray(response.data) ? response.data.sort((a, b) => a.id - b.id) : []
                setData(sortedData)
                if (sortedData.length > 0) {
                    setColumns(Object.keys(sortedData[0]))
                }
                setError(null)
            })
            .catch(error => {
                console.error('Error :', error)
                setError(error.response ? `Error: ${error.response.data.message}` : 'An error occurred while fetching data.')
            })
    }

    const handleInit = () => {
        axios.post('http://localhost:8080/init')
            .then(response => {
                console.log('Init response:', response.data)
                setError(null)
            })
            .catch(error => {
                console.error('Error :', error)
                setError(error.response ? `Error: ${error.response.data.message}` : 'An error occurred during initialization.')
            })
    }

    const handleRelatedIdClick = (relatedClass, id) => {
        const formattedClass = formatRouteName(relatedClass)
        axios.get(`http://localhost:8080/${formattedClass}/${id}`)
            .then(response => {
                console.log(`Details for ${formattedClass} ID ${id}:`, response.data)
                setRelatedDetails(response.data)
                setShowRelatedDetails(true)
                setError(null)
            })
            .catch(error => {
                console.error('Error :', error)
                setError(error.response ? `Error: ${error.response.data.message}` : 'An error occurred while fetching related details.')
            })
    }

    const renderCell = (value, column) => {
        if (['year', 'leaveBalance'].includes(column)) {
            return value
        }
        if (column !== 'id' && typeof value === 'number') {
            return (
                <button
                    onClick={() => handleRelatedIdClick(column, value)}
                    className="text-blue-500 underline"
                >
                    {value}
                </button>
            )
        }
        if (Array.isArray(value)) {
            return (
                <div>
                    {value.map((id, index) => (
                        <button
                            key={index}
                            onClick={() => handleRelatedIdClick(column, id)}
                            className="text-blue-500 underline mr-2"
                        >
                            {id}
                        </button>
                    ))}
                </div>
            )
        }
        if (typeof value === 'boolean') {
            return value ? 'True' : 'False'
        }
        return value
    }

    return (
        <>
            <header className="flex items-center justify-between p-5 bg-gray-200 z-10">
                <div className="flex items-center">
                    <a href="https://vite.dev" target="_blank">
                        <img src={viteLogo} className="logo" alt="Vite logo"/>
                    </a>
                    <a href="https://react.dev" target="_blank">
                        <img src={reactLogo} className="logo react" alt="React logo"/>
                    </a>
                </div>
                <h1 className="text-3xl">SIRH</h1>
                <div>
                    <button onClick={() => fetchData('employees')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Employees
                    </button>
                    <button onClick={() => fetchData('evaluations')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Evaluations
                    </button>
                    <button onClick={() => fetchData('feedbacks')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Feedbacks
                    </button>
                    <button onClick={() => fetchData('leaverequests')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">LeaveRequests
                    </button>
                    <button onClick={() => fetchData('objectives')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Objectives
                    </button>
                    <button onClick={() => fetchData('positions')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Positions
                    </button>
                    <button onClick={() => fetchData('skills')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Skills
                    </button>
                    <button onClick={() => fetchData('teams')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Teams
                    </button>
                    <button onClick={() => fetchData('trainings')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Trainings
                    </button>
                    <button onClick={() => fetchData('trainingrequests')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">TrainingRequests
                    </button>
                </div>
                <button onClick={handleInit} className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded m-2">Init</button>
            </header>
            <div className="h-screen flex flex-col items-center justify-center">
                {error && <div className="text-red-500 mb-4">{error}</div>}
                {!showRelatedDetails ? (
                    <>
                        {selectedClass && <h2 className="text-2xl mb-4">{selectedClass}</h2>}
                        <div className="overflow-y-auto max-h-[70vh] w-full">
                            <table className="table-auto border-collapse border border-gray-400 mt-5 w-full">
                                <thead>
                                <tr>
                                    {columns.map(column => (
                                        <th key={column} className="border border-gray-300 px-4 py-2">{column}</th>
                                    ))}
                                </tr>
                                </thead>
                                <tbody>
                                {data.map(item => (
                                    <tr key={item.id}>
                                        {columns.map(column => (
                                            <td key={column}
                                                className="border border-gray-300 px-4 py-2">{renderCell(item[column], column)}</td>
                                        ))}
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    </>
                ) : (
                    <div className="p-4 border border-gray-400">
                        <button
                            onClick={() => setShowRelatedDetails(false)}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
                        >
                            Back
                        </button>
                        <h3 className="text-xl mb-2">Related Details</h3>
                        <pre>{JSON.stringify(relatedDetails, null, 2)}</pre>
                    </div>
                )}
            </div>
        </>
    )
}

export default App