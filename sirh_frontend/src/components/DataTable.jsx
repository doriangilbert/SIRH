import React from 'react'

const DataTable = ({ columns, data, renderCell }) => {
    return (
        <div className="overflow-y-auto w-full">
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
                            <td key={column} className="border border-gray-300 px-4 py-2">
                                {renderCell(item[column], column)}
                            </td>
                        ))}
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default DataTable