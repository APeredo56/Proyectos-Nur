import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Input } from "@material-tailwind/react";

type SearchBarProps = {
    containerClassNames?: string,
    inputClassNames?: string,
    setSearchTerm: (searchTerm: string) => void,
}

const SearchBarComponent = ({ containerClassNames, inputClassNames, setSearchTerm }: SearchBarProps) => {
    const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearchTerm(e.target.value);
    }

    return (<div className={`w-full md:w-72 ${containerClassNames}`}>
        <Input label="Buscar" className={inputClassNames} onChange={handleSearchChange}
            icon={<FontAwesomeIcon icon={faMagnifyingGlass} className="h-5 w-5" />} />
    </div>);
}

export default SearchBarComponent;